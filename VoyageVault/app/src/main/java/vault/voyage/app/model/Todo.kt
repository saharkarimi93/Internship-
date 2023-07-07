package vault.voyage.app.model

import vault.voyage.app.LoginActivity

class Todo() {
    var undoneTasks = ArrayList<Task>()
    var doneTasks = ArrayList<Task>()


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