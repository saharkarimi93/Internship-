package vault.voyage.app.panelFragments.todoFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.PanelActivity.Companion.user
import vault.voyage.app.R
import vault.voyage.app.model.Task
import vault.voyage.app.model.Todo
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.todoFragment.completetasks.CompletedTaskFragment
import vault.voyage.app.panelFragments.todoFragment.recyclerview.TasksAdapter


class TodoFragment(user: User) : Fragment() {
    var completedItems_fragment:Fragment = CompletedTaskFragment(user.todoList)
    private var items: Todo = user.todoList
    private lateinit var adapter: TasksAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setHasOptionsMenu(true)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_todo, container, false)
        activity?.setTitle("Todo")
        var todoToolbar: Toolbar = view.findViewById(R.id.todo_toolbar)
        var activity:AppCompatActivity = getActivity() as AppCompatActivity
        activity.setSupportActionBar(todoToolbar)

        val todoItems_recyclerView:RecyclerView = view.findViewById(R.id.todo_items_recyclerview)
        val addButton:Button = view.findViewById(R.id.add_todo_button)
        val todo_editText:EditText = view.findViewById(R.id.todo_editText)
        todoItems_recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TasksAdapter(context,items.getTasks(),user,activity)
        todoItems_recyclerView.adapter =adapter
        addButton.setOnClickListener {
            if(todo_editText.text.isNotEmpty()){
                items.getTasks().add(0, Task(todo_editText.text.toString()))

                todo_editText.text.clear()
                Toast.makeText(context,"New Todo Item Added",Toast.LENGTH_LONG).show()
                adapter.notifyDataSetChanged()
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