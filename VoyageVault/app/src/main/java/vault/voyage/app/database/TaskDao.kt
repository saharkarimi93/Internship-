package vault.voyage.app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import vault.voyage.app.model.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM tasks WHERE task_status=:done AND username = :username ")
    fun getTasks(username:String,done:Boolean):LiveData<List<Task>>

    @Query("DELETE FROM tasks WHERE username =:username AND id =:id")
    fun removeUserTask(username:String,id: UUID)

}