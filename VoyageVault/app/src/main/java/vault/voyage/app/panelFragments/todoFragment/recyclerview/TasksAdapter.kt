package vault.voyage.app.panelFragments.todoFragment.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.todoFragment.TaskFullInformation


class TasksAdapter (
    val context: Context?,
    var items:MutableList<Task>,
    val user: User,
    val activity: AppCompatActivity
): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TasksAdapter.ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.desc.text = currentItem.description
        val menu:PopupMenu = PopupMenu(context,holder.options)
        activity.menuInflater.inflate(R.menu.task_menu,menu.menu)
        menu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete_task_menuItem ->{
                    user.todoList.removeTask(currentItem)
                    Toast.makeText(context,"Task Deleted",Toast.LENGTH_SHORT).show()
                    activity.runOnUiThread {
                        notifyDataSetChanged()
                    }
                }
                R.id.complete_task_menuItem->{
                    user.todoList.doneTask(position)
                    Toast.makeText(context,"Task Completed successfully",Toast.LENGTH_SHORT).show()
                    activity.runOnUiThread {
                        notifyDataSetChanged()
                    }
                }
            }
            true
        }
        holder.options.setOnClickListener {
            menu.show()
        }
        holder.item_card.setOnClickListener {
            val intent = Intent(context,TaskFullInformation::class.java)
            intent.putExtra("DESCRIPTION",currentItem.description)
            activity.startActivity(intent)

        }


    }
    inner class ViewHolder(todoItemView: View): RecyclerView.ViewHolder(todoItemView) {
        val title = todoItemView.findViewById<TextView>(R.id.task_title)
        val desc = todoItemView.findViewById<TextView>(R.id.task_information)
        val options = todoItemView.findViewById<TextView>(R.id.options)
        val item_card = todoItemView.findViewById<ConstraintLayout>(R.id.item_card)

    }
}