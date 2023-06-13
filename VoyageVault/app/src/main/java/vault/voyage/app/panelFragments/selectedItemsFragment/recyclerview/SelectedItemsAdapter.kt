package vault.voyage.app.panelFragments.selectedItemsFragment.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R
import vault.voyage.app.model.User
import vault.voyage.app.panelFragments.selectedItemsFragment.Category

class SelectedItemsAdapter(
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
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName= itemView.findViewById<TextView>(R.id.item_category_mybag)
        val picture = itemView.findViewById<ImageView>(R.id.item_image_mybag)
    }
}