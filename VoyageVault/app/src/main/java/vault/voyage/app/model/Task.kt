package vault.voyage.app.model

class Task(val description:String) {
    private var done:Boolean = false

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