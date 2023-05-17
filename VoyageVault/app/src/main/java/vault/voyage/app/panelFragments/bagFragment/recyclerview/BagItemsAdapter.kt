package vault.voyage.app.panelFragments.bagFragment.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.R

class BagItemsAdapter(val context:Context,val items:List<BagItem>): RecyclerView.Adapter<BagItemsAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image = itemView.findViewById<ImageView>(R.id.item_image)
        val title = itemView.findViewById<TextView>(R.id.item_title)

        fun bindItem(item:BagItem,context:Context){
            val resourceId = context.resources.getIdentifier(item.image,"drawable",context.packageName)
            image.setImageResource(resourceId)
            title.text = item.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.bag_items,parent,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(items[position],context)
    }
}