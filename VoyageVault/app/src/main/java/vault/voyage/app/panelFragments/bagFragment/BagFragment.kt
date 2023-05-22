package vault.voyage.app.panelFragments.bagFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItem
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItemsAdapter


class BagFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_bag, container, false)
        val bag_recyclerview = view.findViewById<RecyclerView>(R.id.bagItems_recyclerview)
        bag_recyclerview.layoutManager = LinearLayoutManager(context)
        //Test
        val items = arrayListOf<BagItem>(
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3")),
            BagItem(R.drawable.backpack_item,"Back Pack", listOf("Item 1","Item 2","Item 3"))
        )
        bag_recyclerview.adapter = BagItemsAdapter(context,items)
        return view;
    }

}