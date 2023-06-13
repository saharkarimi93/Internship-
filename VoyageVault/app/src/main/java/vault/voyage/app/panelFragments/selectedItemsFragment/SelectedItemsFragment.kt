package vault.voyage.app.panelFragments.selectedItemsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import vault.voyage.app.PanelActivity
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.selectedItemsFragment.recyclerview.SelectedItemsAdapter

class SelectedItemsFragment(val user: User) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*
BagItem(R.drawable.clothing,"Clothing", clothingList, R.color.bag_Clothing),
            BagItem(R.drawable.personal_care,"Personal Care", personalCareList,Color.rgb(217,242,255)),
            BagItem(R.drawable.baby_needs,"Baby Needs", babyNeedsList,Color.rgb(255,255,209)),
            BagItem(R.drawable.health,"Health", healthList,Color.rgb(208,255,209)),
            BagItem(R.drawable.technology,"Technology", technologyList,Color.rgb(208,255,142)),
            BagItem(R.drawable.food,"Food", foodList,Color.rgb(252,228,149)),
            BagItem(R.drawable.beach_supplies,"Beach Supplies", beachSuppliesList,Color.rgb(173,247,240)),
            BagItem(R.drawable.car_supplies,"Car Supplies", carSuppliesList,Color.rgb(217,255,240)),
            BagItem(R.drawable.needs,"Needs", needsList,Color.rgb(216,247,216)),
 */
        val view =  inflater.inflate(R.layout.fragment_selected_items, container, false)
        val recyclerview:RecyclerView = view.findViewById(R.id.categories_recyclerView)
        recyclerview.layoutManager = GridLayoutManager(context,3)
        val categories: List<Category> = listOf(
            Category("Clothing",R.drawable.clothing),
            Category("Personal Care",R.drawable.personal_care),
            Category("Baby Needs",R.drawable.baby_needs),
            Category("Health",R.drawable.health),
            Category("Technology",R.drawable.technology),
            Category("Food",R.drawable.food),
            Category("Beach Supplies",R.drawable.beach_supplies),
            Category("Car Supplies",R.drawable.car_supplies),
            Category("Needs",R.drawable.needs)
        )
        recyclerview.adapter = SelectedItemsAdapter(context,user,categories)


        return view
    }


}