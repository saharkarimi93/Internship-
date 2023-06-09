package vault.voyage.app.panelFragments.bagFragment.bagSelectedItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R


class BagSelectedItemFragment(
    val items:MutableList<SelectedItem>
) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_bag_selected_item, container, false)
        val recyclerView:RecyclerView = view.findViewById(R.id.selected_bag_items_recycler_View)
        recyclerView.adapter = BagSelectedItemAdapter(context, activity as AppCompatActivity,items)
        recyclerView.layoutManager = LinearLayoutManager(context)



        return view
    }

}