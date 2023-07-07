package vault.voyage.app.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("selectedItems")
data class SelectedItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val username:String,
    val info:String,
    val category:String) {
    @Ignore constructor(username: String,info: String,category: String) : this(null,username,info,category)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SelectedItem

        if (username != other.username) return false
        if (info != other.info) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = info.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }


}