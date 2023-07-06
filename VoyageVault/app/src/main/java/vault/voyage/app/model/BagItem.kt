package vault.voyage.app.model

import android.graphics.Color
import androidx.room.Entity
import androidx.room.Ignore
import vault.voyage.app.model.SelectedItem
data class BagItem(
    val image:Int,
    val title:String,
    val items:MutableList<SelectedItem>,
) {
    val color = Color.rgb(231,243,252)
}