package vault.voyage.app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import vault.voyage.app.model.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Upsert
    fun upsertTask(task: Task)
    @Query("SELECT * FROM tasks WHERE username=:username ")
    fun getTasks(username:String):List<Task>
    @Query("SELECT * FROM tasks WHERE task_status=:done AND username = :username ")
    fun getTasks(username:String,done:Int):LiveData<List<Task>>

    @Query("DELETE FROM tasks WHERE username =:username AND id =:id")
    fun deleteUserTask(username:String, id: UUID)
    @Query("DELETE FROM tasks WHERE username=:username")
    fun deleteAllTasks(username:String)

    @Insert
    fun completeTask(task:Task)

}