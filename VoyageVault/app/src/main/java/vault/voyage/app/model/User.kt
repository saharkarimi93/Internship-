package vault.voyage.app.model

import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber

class User(
    username:String,
    firstname:String,
    lastname:String,
    email:String,
    password:String,
    number:String){
    var todoList = Todo()
    var userBag:MutableSet<SelectedItem> = mutableSetOf()
    var username = username
        get() = field
        set(value) {field=value}
    var password = password
        get() = field
        set(value) {field=value}
    var firstname = firstname
        get() = field
        set(value) {field = value}
    var lastname = lastname
        get() = field
        set(value) {field=value}
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
            val pattern = Regex(" ^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$ ")
            if(value.toString().length==10 && pattern.matches(value)){

                field = value
            }else{
                throw InvalidPhoneNumber("$value is invalid as phone number")
            }
        }

}