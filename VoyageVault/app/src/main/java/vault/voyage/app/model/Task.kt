package vault.voyage.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("tasks")
data class Task(
    @PrimaryKey                       val id:UUID
    ,@ColumnInfo(name = "username")    val username:String
    ,@ColumnInfo(name = "task_title")  val title: String
    ,@ColumnInfo(name = "task_desc")   val description:String
    ,@ColumnInfo(name = "task_status") var done:Boolean
) {


    fun doneTask(){
        done = true
    }
    fun unDoneTask(){
        done = false
    }
    fun isDone():Boolean{
        return done
    }
}