package vault.voyage.app.panelFragments.todoFragment.recyclerview

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R


class TodoItemsAdapter (val context: Context?, var items:MutableList<TodoItem>): RecyclerView.Adapter<TodoItemsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemsAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: TodoItemsAdapter.ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.desc.text = currentItem.description
        holder.done_task.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                Toast.makeText(context,"Task is Done",Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }
        }

    }
    inner class ViewHolder(todoItemView: View): RecyclerView.ViewHolder(todoItemView) {
        val done_task = todoItemView.findViewById<CheckBox>(R.id.task_done_checkbox)

        val desc = todoItemView.findViewById<TextView>(R.id.task_information)

    }
}