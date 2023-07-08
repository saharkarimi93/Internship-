package vault.voyage.app.panelFragments.todoFragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.LoginActivity
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.Todo
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.todoFragment.completetasks.CompletedTaskFragment
import vault.voyage.app.panelFragments.todoFragment.recyclerview.TasksAdapter
import java.time.LocalDate
import java.util.UUID


class TodoFragment(var user: User) : Fragment() {
    var completedItems_fragment:Fragment = CompletedTaskFragment(user)
    private var items: Todo = user.todoList
    private lateinit var adapter: TasksAdapter
    private lateinit var theDay:TextView
    private lateinit var nextDay:FloatingActionButton
    private lateinit var previousDay:FloatingActionButton
    private lateinit var date:LocalDate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setHasOptionsMenu(true)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_todo, container, false)
        activity?.setTitle("Todo")
        var todoToolbar: Toolbar = view.findViewById(R.id.todo_toolbar)

        theDay = view.findViewById(R.id.theDay)
        nextDay = view.findViewById(R.id.nextDay)
        previousDay = view.findViewById(R.id.previousDay)
        theDay.setText(LocalDate.now().toString())
        date = LocalDate.now()
        var activity:AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(todoToolbar)

        val todoItems_recyclerView:RecyclerView = view.findViewById(R.id.todo_items_recyclerview)


        todoItems_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TasksAdapter(context,items.undoneTasks,user,activity)
        todoItems_recyclerView.adapter =adapter
        var addTaskFab = view.findViewById<ExtendedFloatingActionButton>(R.id.addTaskFab)
        addTaskFab.setOnClickListener{
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_todo,null)
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context).setTitle("Lets Add New Task!").setView(dialogView)

            val alertDialog = dialogBuilder.show()

            val addTask = dialogView.findViewById<Button>(R.id.add_task_button)
            val cancel = dialogView.findViewById<Button>(R.id.cancel_add_task)
            val title_dialog = dialogView.findViewById<EditText>(R.id.task_title_dialog)
            val desc_dialog = dialogView.findViewById<EditText>(R.id.task_desc_dialog)
            cancel.setOnClickListener {
                alertDialog.dismiss()
            }
            addTask.setOnClickListener {
                val task_title = title_dialog.text.toString()
                val task_desc = desc_dialog.text.toString()


                val newTask = Task(UUID.randomUUID(),user.username,task_title,task_desc,false,date)
                if(task_title.isNotEmpty() && task_desc.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        LoginActivity.db.tasks.upsertTask(newTask)
                    }
                    user.todoList.undoneTasks.add(newTask)
                    adapter.notifyDataSetChanged()
                    alertDialog.dismiss()
                }else{
                    if(task_title.isEmpty() && task_desc.isEmpty()){
                        Toast.makeText(context,"All Fields Are Empty",Toast.LENGTH_SHORT).show()
                    }else if(task_desc.isEmpty()){
                        Toast.makeText(context,"Description Field is Empty",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Title Field is Empty",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return view
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.todo_toolbar,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.remove_all_tasks->{
                items.clearList()
                CoroutineScope(Dispatchers.IO).launch {
                    LoginActivity.db.tasks.deleteUndoneTasks(user.username)
                }
                adapter.notifyDataSetChanged()
                Toast.makeText(context,"All Items Removed",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.done_tasks_list->{
                switchFragment(completedItems_fragment)
                Toast.makeText(context,"Completed Tasks List",Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun switchFragment(fragment:Fragment):Boolean{
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.panels_container,fragment)
            ?.commit()
        return true

    }
}