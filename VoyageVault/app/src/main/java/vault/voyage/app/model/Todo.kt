package vault.voyage.app.model

class Todo() {
    private var allTodo = ArrayList<Task>()
    private val doneTask = ArrayList<Task>()



    fun restore(pos:Int):Unit{
        allTodo.add(doneTask.get(pos))
    }
    fun clearList(){
        allTodo.clear()
    }
    fun doneTask(pos: Int):Unit{
        allTodo.get(pos).doneTask()
        doneTask.add(allTodo.get(pos))
    }
    fun removeDoneTask(pos:Int){
        allTodo.get(pos).unDoneTask()
        doneTask.removeAt(pos)
    }
}