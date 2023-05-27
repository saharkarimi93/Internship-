package vault.voyage.app.panelFragments.todoFragment.recyclerview

class TodoItem(val description:String) {
    private var done:Boolean = false

    fun doneTask(){
        done = true
    }
    fun isDone():Boolean{
        return done
    }

}