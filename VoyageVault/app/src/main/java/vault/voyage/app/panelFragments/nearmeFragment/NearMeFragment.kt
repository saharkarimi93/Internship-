package vault.voyage.app.panelFragments.nearmeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.nearmeFragment.recyclerview.NearMeAdapter

class NearMeFragment(val user: User) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_near_me, container, false)
        //Reference: VectorStock.com
        val background = view.findViewById<ImageView>(R.id.near_me_bg)
        background.setImageResource(R.drawable.near_me_bg)
        val nearMeIcon = view.findViewById<ImageView>(R.id.near_me_icon)
        nearMeIcon.setImageResource(R.drawable.near_me_icon)
        val nearMeRecyclerview = view.findViewById<RecyclerView>(R.id.near_me_recyclerview)


        val places = mutableListOf<NearMeItem>(
            NearMeItem(R.drawable.near_me_breakfast,"BreakFast"),
            NearMeItem(R.drawable.near_me_coffee,"Coffee"),
            NearMeItem(R.drawable.near_me_fuel,"Fuel"),
            NearMeItem(R.drawable.near_me_gym,"Gym"),
            NearMeItem(R.drawable.near_me_park,"Park"),
            NearMeItem(R.drawable.near_me_bank,"Bank"),
        )
        nearMeRecyclerview.adapter = NearMeAdapter(activity as AppCompatActivity?,context,user,places)
        return view
    }
}