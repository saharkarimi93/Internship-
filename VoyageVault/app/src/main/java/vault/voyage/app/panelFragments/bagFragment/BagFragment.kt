package vault.voyage.app.panelFragments.bagFragment

import android.graphics.Color
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
            BagItem(R.drawable.clothing,"Clothing", listOf("Item 1","Item 2","Item 3"), Color.rgb(221,251,162)),
            BagItem(R.drawable.personal_care,"Personal Care", listOf("Item 1","Item 2","Item 3"),Color.rgb(217,242,255)),
            BagItem(R.drawable.baby_needs,"Baby Needs", listOf("Item 1","Item 2","Item 3"),Color.rgb(255,255,209)),
            BagItem(R.drawable.health,"Health", listOf("Item 1","Item 2","Item 3"),Color.rgb(208,255,209)),
            BagItem(R.drawable.technology,"Technology", listOf("Item 1","Item 2","Item 3"),Color.rgb(208,255,142)),
            BagItem(R.drawable.food,"Food", listOf("Item 1","Item 2","Item 3"),Color.rgb(252,228,149)),
            BagItem(R.drawable.beach_supplies,"Beach Supplies", listOf("Item 1","Item 2","Item 3"),Color.rgb(173,247,240)),
            BagItem(R.drawable.car_supplies,"Car Supplies", listOf("Item 1","Item 2","Item 3"),Color.rgb(217,255,240)),
            BagItem(R.drawable.needs,"Needs", listOf("Item 1","Item 2","Item 3"),Color.rgb(216,247,216)),
        )
        bag_recyclerview.adapter = BagItemsAdapter(context,items)
        return view;
    }

}