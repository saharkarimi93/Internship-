package vault.voyage.app.panelFragments.bagFragment.recyclerview

import android.widget.ImageView
import vault.voyage.app.R
import vault.voyage.app.panelFragments.bagFragment.bagSelectedItem.SelectedItem

data class BagItem(
    val image:Int,
    val title:String,
    val items:MutableList<SelectedItem>,
    val color:Int) {
}