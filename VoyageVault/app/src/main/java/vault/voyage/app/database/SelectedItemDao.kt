package vault.voyage.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vault.voyage.app.model.SelectedItem

@Dao
interface SelectedItemDao {
    @Insert
    fun insert(item:SelectedItem)
    @Query("DELETE FROM selectedItems WHERE username=:username AND info=:info AND category= :category ")
    fun delete(username: String,info:String,category: String)
    @Query("SELECT * FROM selectedItems where username=:username")
    fun getAllSelectedItems(username:String):List<SelectedItem>
}