package vault.voyage.app.panelFragments.bagFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.bagFragment.bagSelectedItem.BagSelectedItemFragment
import vault.voyage.app.panelFragments.bagFragment.bagSelectedItem.SelectedItem
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItem
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItemsAdapter


class BagFragment(user: User) : Fragment() {


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
            BagItem(R.drawable.clothing,"Clothing", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")), R.color.bag_Clothing),
            BagItem(R.drawable.personal_care,"Personal Care", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(217,242,255)),
            BagItem(R.drawable.baby_needs,"Baby Needs", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(255,255,209)),
            BagItem(R.drawable.health,"Health", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(208,255,209)),
            BagItem(R.drawable.technology,"Technology", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(208,255,142)),
            BagItem(R.drawable.food,"Food", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(252,228,149)),
            BagItem(R.drawable.beach_supplies,"Beach Supplies", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(173,247,240)),
            BagItem(R.drawable.car_supplies,"Car Supplies", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(217,255,240)),
            BagItem(R.drawable.needs,"Needs", mutableListOf(SelectedItem("Item 1"),SelectedItem("Item 2"),SelectedItem("Item 3")),Color.rgb(216,247,216)),
        )
        bag_recyclerview.adapter = BagItemsAdapter(context, activity as AppCompatActivity,items)




        return view;
    }


}