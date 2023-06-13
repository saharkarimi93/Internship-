package vault.voyage.app.panelFragments.selectedItemsFragment

import android.widget.ImageView
import vault.voyage.app.model.SelectedItem
import vault.voyage.app.model.User

data class Category(
    val categoryName:String,
    val image:Int,
    val set:MutableSet<SelectedItem>
) {
}