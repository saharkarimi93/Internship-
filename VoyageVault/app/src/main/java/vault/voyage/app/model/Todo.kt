package vault.voyage.app.model

import kotlin.streams.toList

class Todo() {
    private val allTodo = ArrayList<Task>()
    fun getUndoneTasks():ArrayList<Task>{
        var list = allTodo.stream()
            .filter{ t -> !t.isDone()}
            .toList() as ArrayList<Task>
        return list
    }
    fun getDoneTasks():ArrayList<Task>{
        var list = allTodo.stream()
            .filter { t -> t.isDone() }
            .toList() as ArrayList<Task>
        return list

    }
    fun getTasks():ArrayList<Task>{
        return allTodo
    }
    fun removeTask(task:Task){
        allTodo.remove(task)
    }
    fun restore(pos:Int):Unit{
        getDoneTasks()[pos].unDoneTask()
    }
    fun clearList(){
        allTodo.clear()
    }
    fun doneTask(pos: Int):Unit{
        allTodo[pos].doneTask()

    }
    fun removeDoneTask( t:Task){
        t.unDoneTask()
    }
}