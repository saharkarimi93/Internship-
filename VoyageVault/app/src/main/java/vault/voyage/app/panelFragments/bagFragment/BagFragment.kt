package vault.voyage.app.panelFragments.bagFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.model.SelectedItem
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItem
import vault.voyage.app.panelFragments.bagFragment.recyclerview.BagItemsAdapter
import kotlin.streams.toList


class BagFragment(val user: User) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_bag, container, false)
        val bag_recyclerview = view.findViewById<RecyclerView>(R.id.bagItems_recyclerview)
        bag_recyclerview.layoutManager = GridLayoutManager(context,2)

        val clothingList = stringToMutableList("Clothing","Stockings,Underwear,Pajamas,T shirts,Casual Dress,Evening Dress,Shirt,Cardigan,Vest,Jacket,Skirt,Trousers,Jeans,Shorts,Suit,Coat,Rain Coat,Glove,Hat, Scarf,Bikini,Belt,Slipper,Sneakers,Casual Shoes,Heeled Shoes,Sports Wear")
        val personalCareList = stringToMutableList("Personal Care","Tooth-brush,Tooth-passe,Floss,Mouthwash,Shaving Cream,Razor Blade, Soap,Fiber,Shampoo,Hair Conditioner,Brush,Comb,Hair Dryer,Curling Iron,Hai moulder,Hair clip, Moisturizer,Lip Cream, Contact Lens,Perfume,Deodorant,Makeup Materials,Makeup Remover,Wet Wipes,Pad,Ear Stick,Cotton,Nail Polish,Nail Polish Remover,Tweezers,Nail Scissors,Nail Files,Suntan cream")
        val babyNeedsList = stringToMutableList("Baby Needs","Snap suit,outfit,jumpsuit,baby socks,baby hat,baby pajamas,Baby Bath Towel, Muslin,Blanket,Dribble Bibs, Breast Pump,Water Bottle,Storage Container, Food Spoon, Highchairs,Diaper,WetWipes,Baby Cotton, Baby Care Cover,Diaper,Stroller,Carrier,Toys")
        val healthList = stringToMutableList("Health","Aspirin,Drugs Used,Vitamins Used,Lens Solutions,First Aid,Pain Reliever,Fever Reducer,Diarrhea Stopper,Pain Relieve Spray")
        val technologyList = stringToMutableList("Technology","Mobile Phone,Phone Cover,E-Book Reader,Camera,Camera Charger,Portable Speaker,IPad,Headphone,Laptop,Laptop charger,Mouse,Extension Cable,Battery,Power Bank,Mp3 Player")
        val foodList =stringToMutableList("Food","Snack,sandwich,Juice,Tea Bags,Coffee,Water,Thermos,Chips,Baby food")
        val beachSuppliesList =stringToMutableList("Beach Supplies","Sea Glasses,Sea Bed,Suntan Cream,Beach Umbrella, Swim Fins,Beach Bag,Beach Towel,Beach Slippers,Sunbed,Snorkel,WaterProof Clock")
        val carSuppliesList =stringToMutableList("Car Supplies","Pump,Car jack,spare car key,accident record set,auto refrigerator, car cover, car charger, window sun shades")
        val needsList = stringToMutableList("Needs","Backpack,Daily Bags,Laundry Bag,sewing kit,travel lock,luggage tag,magazine,sports equipment, important numbers")



        val items = arrayListOf<BagItem>(
            BagItem(R.drawable.clothing,"Clothing", clothingList, R.color.bag_Clothing),
            BagItem(R.drawable.personal_care,"Personal Care", personalCareList,Color.rgb(217,242,255)),
            BagItem(R.drawable.baby_needs,"Baby Needs", babyNeedsList,Color.rgb(255,255,209)),
            BagItem(R.drawable.health,"Health", healthList,Color.rgb(208,255,209)),
            BagItem(R.drawable.technology,"Technology", technologyList,Color.rgb(208,255,142)),
            BagItem(R.drawable.food,"Food", foodList,Color.rgb(252,228,149)),
            BagItem(R.drawable.beach_supplies,"Beach Supplies", beachSuppliesList,Color.rgb(173,247,240)),
            BagItem(R.drawable.car_supplies,"Car Supplies", carSuppliesList,Color.rgb(217,255,240)),
            BagItem(R.drawable.needs,"Needs", needsList,Color.rgb(216,247,216)),
        )
        bag_recyclerview.adapter = BagItemsAdapter(context, activity as AppCompatActivity,items,user)

        return view;
    }

    fun stringToMutableList(category:String,inputString:String): MutableList<SelectedItem> {
        return inputString
            .split(",")
            .stream()
            .map { e-> SelectedItem(e,category) }
            .toList()
            .toMutableList()

    }


}