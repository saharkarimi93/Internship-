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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.LoginActivity
import vault.voyage.app.R
import vault.voyage.app.model.SelectedItem
import vault.voyage.app.model.User

class BagSelectedItemAdapter(
    val context: Context?,
    val activity: AppCompatActivity,
    var items:MutableList<SelectedItem>,
    val user:User
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
        holder.options.setText("➕")
        holder.options.setOnClickListener {
            val itemAdded:Boolean = user.userBag.add(currentItem)
            if(itemAdded) {
                CoroutineScope(Dispatchers.IO).launch {
                    LoginActivity.db.selectedItems.insert(currentItem)
                }
                activity.runOnUiThread{
                    Toast.makeText(context, "Item Added to your Bag", Toast.LENGTH_SHORT).show()
                }
            }else{
                activity.runOnUiThread{
                    Toast.makeText(context,"Item Exists in your bag",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bag_item_info = itemView.findViewById<TextView>(R.id.bag_item_info)
        val options = itemView.findViewById<TextView>(R.id.bag_item_options)
    }
}