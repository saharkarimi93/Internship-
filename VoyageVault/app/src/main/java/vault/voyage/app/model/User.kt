package vault.voyage.app.model

import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber

class User(name:String,email:String, number:String){
    var todoList = Todo()
    var userBag:MutableSet<SelectedItem> = mutableSetOf()
    var name = name
        get() = field
        set(value) {field = value}
    var email = email
        get() = field
        set(value) {
            val matchPattern = Regex("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$");
            if(matchPattern.matches(value)) {
                field = value
            }else{
                throw InvalidEmailException("$value is Invalid as Email Address")
            }
        }
    var number = number
        get() = field
        set(value) {
            val pattern = Regex("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}\$")
            if(value.toString().length==10 && pattern.matches(value)){

                field = value
            }else{
                throw InvalidPhoneNumber("$value is invalid as phone number")
            }
        }

}