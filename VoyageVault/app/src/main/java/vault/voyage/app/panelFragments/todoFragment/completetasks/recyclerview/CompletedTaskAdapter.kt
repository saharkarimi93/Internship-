package vault.voyage.app.panelFragments.todoFragment.completetasks.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.Todo

class CompletedTaskAdapter (val context: Context?, var items:Todo): RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTaskAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.completed_task_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.getDoneTasks().count()
    }

    override fun onBindViewHolder(holder: CompletedTaskAdapter.ViewHolder, position: Int) {
        val currentItem = items.getDoneTasks()[position]
        holder.desc.text = currentItem.description
        holder.restore.setOnClickListener {
            items.restore(position)
            notifyDataSetChanged()
        }
        holder.delete.setOnClickListener {
            items.removeTask(currentItem)
            notifyDataSetChanged()
        }

    }
    inner class ViewHolder(todoItemView: View): RecyclerView.ViewHolder(todoItemView) {
        val title = todoItemView.findViewById<TextView>(R.id.done_task_title)
        val desc = todoItemView.findViewById<TextView>(R.id.done_task_info)
        val restore = todoItemView.findViewById<ImageButton>(R.id.restore_button)
        val delete = todoItemView.findViewById<ImageButton>(R.id.delete_button)

    }
}