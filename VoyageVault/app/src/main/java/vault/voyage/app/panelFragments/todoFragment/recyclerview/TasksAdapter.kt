package vault.voyage.app.panelFragments.todoFragment.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.User


class TasksAdapter (val context: Context?, var items:MutableList<Task>,val user: User): RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
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
        holder.done_task.isChecked = currentItem.isDone()

        holder.desc.text = currentItem.description
        holder.done_task.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                user.todoList.doneTask(position)
                Toast.makeText(context,"Task is Done",Toast.LENGTH_SHORT).show()

            }else{
                user.todoList.removeDoneTask(currentItem)
            }
        }

    }
    inner class ViewHolder(todoItemView: View): RecyclerView.ViewHolder(todoItemView) {
        val done_task = todoItemView.findViewById<CheckBox>(R.id.task_done_checkbox)

        val desc = todoItemView.findViewById<TextView>(R.id.task_information)

    }
}