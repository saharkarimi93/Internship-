package vault.voyage.app.panelFragments.todoFragment.completetasks.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Task

class CompletedTaskAdapter (val context: Context?, var items:MutableList<Task>): RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTaskAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.todo_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CompletedTaskAdapter.ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.desc.text = currentItem.description
        holder.restore.setOnClickListener {

        }

    }
    inner class ViewHolder(todoItemView: View): RecyclerView.ViewHolder(todoItemView) {
        val desc = todoItemView.findViewById<TextView>(R.id.done_task_info)
        val restore = todoItemView.findViewById<Button>(R.id.restore_button)
        val delete = todoItemView.findViewById<Button>(R.id.delete_button)

    }
}