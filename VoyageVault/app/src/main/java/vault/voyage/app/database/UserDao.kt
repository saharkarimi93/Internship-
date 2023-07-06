package vault.voyage.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import vault.voyage.app.model.User

@Dao
interface UserDao {
    //Inserts new User or updates the exist user
    @Upsert
    suspend fun upsertUser(user:User)



}