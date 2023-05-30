package vault.voyage.app.panelFragments.todoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.panelFragments.todoFragment.recyclerview.TodoItem
import vault.voyage.app.panelFragments.todoFragment.recyclerview.TodoItemsAdapter


class TodoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_todo, container, false)
        val todoItems_recyclerView:RecyclerView = view.findViewById(R.id.todo_items_recyclerview)
        val addButton:Button = view.findViewById(R.id.add_todo_button)
        val todo_editText:EditText = view.findViewById(R.id.todo_editText)
        todoItems_recyclerView.layoutManager = LinearLayoutManager(context)
        val items:MutableList<TodoItem> = arrayListOf(TodoItem("Test 1"),TodoItem("Test 2"),TodoItem("Test 3"))
        val adapter = TodoItemsAdapter(context,items)
        todoItems_recyclerView.adapter =adapter
        addButton.setOnClickListener {
            if(!todo_editText.text.isEmpty()){
                items.add(0,TodoItem(todo_editText.text.toString()))
                todo_editText.text.clear()
                Toast.makeText(context,"New Todo Item Added",Toast.LENGTH_LONG).show()
                adapter.notifyItemRangeChanged(0,items.count())
            }
        }
        return view
    }

}