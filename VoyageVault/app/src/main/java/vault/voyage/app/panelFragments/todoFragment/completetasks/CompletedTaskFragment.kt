package vault.voyage.app.panelFragments.todoFragment.completetasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Todo
import vault.voyage.app.panelFragments.todoFragment.completetasks.recyclerview.CompletedTaskAdapter


class CompletedTaskFragment(val todo: Todo) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v:View =inflater.inflate(R.layout.fragment_completed_task, container, false)
        val recyclerview = v.findViewById<RecyclerView>(R.id.completed_tasks_recycler_view)
        recyclerview.layoutManager = LinearLayoutManager(context)
        recyclerview.adapter = CompletedTaskAdapter(context,todo)




        return v
    }
}