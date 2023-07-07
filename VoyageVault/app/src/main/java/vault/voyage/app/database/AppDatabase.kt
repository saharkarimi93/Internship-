package vault.voyage.app.database


import androidx.room.Database
import androidx.room.RoomDatabase
import vault.voyage.app.model.SelectedItem
import vault.voyage.app.model.Task
import vault.voyage.app.model.User

@Database(
    entities = [User::class, Task::class, SelectedItem::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract val users:UserDao
    abstract val tasks:TaskDao
    abstract val selectedItems:SelectedItemDao

}
