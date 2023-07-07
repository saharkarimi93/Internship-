package vault.voyage.app.panelFragments.todoFragment.completetasks.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.LoginActivity
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.Todo
import vault.voyage.app.model.User

class CompletedTaskAdapter (val context: Context?, val user: User): RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTaskAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.completed_task_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return user.todoList.doneTasks.count()
    }

    override fun onBindViewHolder(holder: CompletedTaskAdapter.ViewHolder, position: Int) {
        val currentItem = user.todoList.doneTasks[position]
        holder.desc.text = currentItem.description
        holder.title.text = currentItem.title
        holder.restore.setOnClickListener {
            user.todoList.restore(position)
            CoroutineScope(Dispatchers.IO).launch {
                currentItem.done = false
                LoginActivity.db.tasks.upsertTask(currentItem)
            }
            notifyDataSetChanged()
        }
        holder.delete.setOnClickListener {
            user.todoList.removeTask(currentItem)
            CoroutineScope(Dispatchers.IO).launch {
                LoginActivity.db.tasks.deleteUserTask(user.username,currentItem.id)
            }
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