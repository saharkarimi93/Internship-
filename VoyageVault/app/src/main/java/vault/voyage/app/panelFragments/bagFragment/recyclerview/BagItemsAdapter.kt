package vault.voyage.app.panelFragments.bagFragment.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.EmptyActivity
import vault.voyage.app.PanelActivity
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.bagFragment.bagSelectedItem.BagSelectedItemFragment

class BagItemsAdapter(
    val context: Context?,
    val activity:AppCompatActivity,
    val items:List<BagItem>,
    val user:User
): RecyclerView.Adapter<BagItemsAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val bag_list_item = itemView.findViewById<LinearLayout>(R.id.bag_list_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.bag_items,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.image.setImageResource(currentItem.image)
        holder.bag_list_item.setBackgroundColor(currentItem.color)
        holder.bag_list_item.setOnClickListener {
            if(currentItem.items.isEmpty()){
                Intent(context,EmptyActivity::class.java).also {
                    context?.startActivity(it)
                }
            }else {
                switchFragment(BagSelectedItemFragment(currentItem.items, user))
            }
        }

    }
    fun switchFragment(fragment: Fragment):Boolean{
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.panels_container,fragment)
            ?.commit()
        return true

    }
}