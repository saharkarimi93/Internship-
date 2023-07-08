package vault.voyage.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity("tasks")
data class Task(
    @PrimaryKey                       val id:UUID
    ,@ColumnInfo(name = "username")    val username:String
    ,@ColumnInfo(name = "task_title")  val title: String
    ,@ColumnInfo(name = "task_desc")   val description:String
    ,@ColumnInfo(name = "task_status") var done:Boolean
    ,@ColumnInfo(name = "task_date")   var date:LocalDate
) {

    @Ignore
    fun doneTask(){
        done = true
    }
    @Ignore
    fun unDoneTask(){
        done = false
    }
    @Ignore
    fun isDone():Boolean{
        return done
    }
}