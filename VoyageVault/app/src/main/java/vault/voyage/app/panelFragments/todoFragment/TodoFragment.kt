package vault.voyage.app.panelFragments.todoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        todoItems_recyclerView.layoutManager = LinearLayoutManager(context)
        val items:List<TodoItem> = arrayListOf(TodoItem("Test 1"),TodoItem("Test 2"),TodoItem("Test 3"))
        todoItems_recyclerView.adapter = TodoItemsAdapter(context,items)
        return view
    }

}