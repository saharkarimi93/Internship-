package vault.voyage.app.panelFragments.bagFragment.recyclerview

import vault.voyage.app.model.SelectedItem

data class BagItem(
    val image:Int,
    val title:String,
    val items:MutableList<SelectedItem>,
    val color:Int) {
}