package vault.voyage.app.panelFragments.bagFragment.bagSelectedItem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import vault.voyage.app.PanelActivity
import vault.voyage.app.R
import vault.voyage.app.model.Task
import java.time.Duration

class BagSelectedItemAdapter(
    val context: Context?,
    val activity: AppCompatActivity,
    var items:MutableList<SelectedItem>,
    ): RecyclerView.Adapter<BagSelectedItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bag_selected_items,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bag_item_info.text = currentItem.info
        val menu: PopupMenu = PopupMenu(context,holder.options)
        activity.menuInflater.inflate(R.menu.bag_items_menu,menu.menu)
        menu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add_selected_item_menuItem ->{
                    Toast.makeText(context,"Item Added to your Bag",Toast.LENGTH_SHORT).show()
                }
                R.id.delete_selected_item_menuItem->{
                    Toast.makeText(context,"Item Deleted Successfully!",Toast.LENGTH_SHORT).show()

                }
            }
            true
        }
        holder.options.setOnClickListener {
            menu.show()
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bag_item_info = itemView.findViewById<TextView>(R.id.bag_item_info)
        val options = itemView.findViewById<TextView>(R.id.bag_item_options)
    }
}