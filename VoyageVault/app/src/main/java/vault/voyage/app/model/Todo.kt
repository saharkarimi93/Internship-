package vault.voyage.app.model

import vault.voyage.app.LoginActivity

class Todo(val user:User) {
    private val undoneTasks = ArrayList<Task>()
    private val doneTasks = ArrayList<Task>()
    private val db = LoginActivity.db
    fun getDoneTasks():ArrayList<Task>{
        return (db.tasks.getTasks(user.username,true).value as ArrayList<Task>?)!!
    }
    fun getUndoneTasks():ArrayList<Task>{
        val tasks = db.tasks.getTasks(user.username,false).value as ArrayList<Task>?
        return if(tasks==null)
            ArrayList<Task>()
        else
            tasks
    }
    fun addTask(task:Task){
        db.tasks.upsertTask(task)
    }
    fun removeTask(task:Task){
        db.tasks.deleteUserTask(user.username,task.id)
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