package vault.voyage.app.panelFragments.selectedItemsFragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.Category
import vault.voyage.app.model.SelectedItem
import vault.voyage.app.model.User
import kotlin.streams.toList

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
            Category("Clothing",R.drawable.clothing,filterUserBagList("Clothing")),
            Category("Personal Care",R.drawable.personal_care,filterUserBagList("Personal Care")),
            Category("Baby Needs",R.drawable.baby_needs,filterUserBagList("Baby Needs")),
            Category("Health",R.drawable.health,filterUserBagList("Health")),
            Category("Technology",R.drawable.technology,filterUserBagList("Technology")),
            Category("Food",R.drawable.food,filterUserBagList("Food")),
            Category("Beach Supplies",R.drawable.beach_supplies,filterUserBagList("Beach Supplies")),
            Category("Car Supplies",R.drawable.car_supplies,filterUserBagList("Car Supplies")),
            Category("Needs",R.drawable.needs,filterUserBagList("Needs"))
        )
        recyclerview.adapter = SelectedItemsAdapter(activity as AppCompatActivity,context,user,categories)


        return view
    }
    private fun filterUserBagList(category:String):MutableList<SelectedItem>{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            user.userBag.stream().filter { e-> e.category == category }.toList().toMutableList()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
    }

}