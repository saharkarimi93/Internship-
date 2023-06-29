package vault.voyage.app.panelFragments.nearmeFragment.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.nearmeFragment.NearMeItem

class NearMeAdapter(
    val activity: AppCompatActivity?,
    val context: Context?,
    val user: User,
    val places:List<NearMeItem>
): RecyclerView.Adapter<NearMeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.near_me_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return places.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = places[position]
        holder.picture.setImageResource(current.image)
        holder.place.text = current.name

    }
    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val picture = itemView.findViewById<ImageView>(R.id.near_me_items_imageview)
        val card = itemView.findViewById<CardView>(R.id.near_me_items_cardview)
        val place = itemView.findViewById<TextView>(R.id.near_me_item_name)
    }


    fun switchFragment(fragment: Fragment):Boolean{
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.panels_container,fragment)
            ?.commit()
        return true

    }
}