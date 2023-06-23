package vault.voyage.app.model

import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber

data class User(
    var username:String,
    var firstname:String,
    var lastname:String,
    var email:String,
    var password:String,
    var number:String){

    constructor():this("","","","","","")
    var todoList = Todo()
    var userBag:MutableSet<SelectedItem> = mutableSetOf()


    fun setUserEmail(value:String) {
        val matchPattern = Regex("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$");
        if(matchPattern.matches(value)) {
            email = value
        }else{
            throw InvalidEmailException("$value is Invalid as Email Address")
        }
    }

    fun setUserNumber(value:String) {
        val pattern = Regex("^\\+?[1-9][0-9]{7,14}")
        if(value.toString().length==10 && pattern.matches(value)){
            number= value
        }else{
            throw InvalidPhoneNumber("$value is invalid as phone number")
        }
    }

}