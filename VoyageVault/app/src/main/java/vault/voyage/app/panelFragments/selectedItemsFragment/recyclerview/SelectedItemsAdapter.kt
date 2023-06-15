package vault.voyage.app.panelFragments.selectedItemsFragment.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.EmptyActivity
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.bagFragment.bagSelectedItem.BagSelectedItemFragment
import vault.voyage.app.panelFragments.selectedItemsFragment.Category

class SelectedItemsAdapter(
    val activity:AppCompatActivity?,
    val context:Context?,
    val user:User,
    val categories:List<Category>
): RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.my_bag_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categories[position]
        holder.categoryName.text = currentItem.categoryName
        holder.picture.setImageResource(currentItem.image)
        holder.card.setOnClickListener{
            if(currentItem.list.isEmpty()) {
                Intent(context, EmptyActivity::class.java).also {
                    context?.startActivity(it)
                }
            }else {
                switchFragment(BagSelectedItemFragment(currentItem.list, user))
            }
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName= itemView.findViewById<TextView>(R.id.item_category_mybag)
        val picture = itemView.findViewById<ImageView>(R.id.item_image_mybag)
        val card = itemView.findViewById<CardView>(R.id.card_item_myBag)
    }
    fun switchFragment(fragment: Fragment):Boolean{
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.panels_container,fragment)
            ?.commit()
        return true

    }
}