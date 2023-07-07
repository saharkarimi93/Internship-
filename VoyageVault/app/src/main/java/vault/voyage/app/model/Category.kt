package vault.voyage.app.model

import vault.voyage.app.model.SelectedItem

data class Category(
    val categoryName:String,
    val image:Int,
    val list:MutableList<SelectedItem>
) {
}