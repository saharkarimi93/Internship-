package vault.voyage.app.model

import kotlin.streams.toList

class Todo() {
    private val undoneTasks = ArrayList<Task>()
    private val doneTasks = ArrayList<Task>()

    fun getDoneTasks():ArrayList<Task>{
        return doneTasks
    }

    fun getTasks():ArrayList<Task>{
        return undoneTasks
    }
    fun removeTask(task:Task){
        undoneTasks.remove(task)
        doneTasks.remove(task)
    }
    fun restore(pos:Int):Unit{
        val task = doneTasks[pos]
        task.unDoneTask()
        doneTasks.remove(task)
        undoneTasks.add(task)
    }
    fun clearList(){
        undoneTasks.clear()
    }
    fun doneTask(pos: Int):Unit{
        val task = undoneTasks[pos]
        task.doneTask()
        doneTasks.add(task)
        undoneTasks.remove(task)

    }

}