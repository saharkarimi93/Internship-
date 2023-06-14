package vault.voyage.app.panelFragments.selectedItemsFragment

import vault.voyage.app.model.SelectedItem

data class Category(
    val categoryName:String,
    val image:Int,
    val list:MutableList<SelectedItem>
) {
}