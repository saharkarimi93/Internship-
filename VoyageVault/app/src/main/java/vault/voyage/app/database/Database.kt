package vault.voyage.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import vault.voyage.app.model.BagItem
import vault.voyage.app.model.Task
import vault.voyage.app.model.User

@Database(
    entities = [User::class, Task::class, BagItem::class],
    version = 1,
    exportSchema = false
)

abstract class Database: RoomDatabase() {
    abstract val users:UserDao
}