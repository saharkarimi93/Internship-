package vault.voyage.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity("completeTasks")
data class CompleteTasks (
     @PrimaryKey                       val id:UUID
    ,@ColumnInfo(name = "username")    val username:String
    ,@ColumnInfo(name = "task_title")  val title: String
    ,@ColumnInfo(name = "task_desc")   val description:String
    ,@ColumnInfo(name = "task_status") var done:Boolean
) {

}