package vault.voyage.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val username:String
    , val title: String
    , val description:String
    , var done:Boolean
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