package vault.voyage.app.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import vault.voyage.app.model.User

@Dao
interface UserDao {
    //Inserts new User or updates the exist user
    @Upsert
    fun UpsertUser(user:User)



    @Query("SELECT COUNT() FROM users where username =:username")
    suspend fun countUser(username:String):Int

    @Query("SELECT * FROM users where LOWER(username) LIKE LOWER(:username) AND password=:password")
    suspend fun loginUser(username:String,password:String):List<User>
    @Query("SELECT COUNT() FROM users ")
    fun usersAmount():Int



}