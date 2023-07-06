package vault.voyage.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vault.voyage.app.model.BagItem
import vault.voyage.app.model.Task
import vault.voyage.app.model.User

@Database(
    entities = [User::class, Task::class, BagItem::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase private constructor(): RoomDatabase() {
    abstract val users:UserDao
    abstract val tasks:TaskDao
    companion object:SingletonHolder<AppDatabase,Context> ({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "voyage-vault.db").build()
    })


}
open class SingletonHolder<T, A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}