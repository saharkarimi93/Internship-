package vault.voyage.app.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPasswordException
import vault.voyage.app.exceptions.InvalidPhoneNumber
@Entity("users")
data class User(
    @PrimaryKey(autoGenerate = false) var username:String,
    var firstname:String,
    var lastname:String,
    var email:String,
    var password:String,
    var number:String){
    @Ignore
    constructor():this("","","","","","")
    @Ignore
    var todoList = Todo()
    @Ignore
    var userBag:MutableSet<SelectedItem> = mutableSetOf()
    fun setUserPassword(value:String){
        if(Validator.validPassword(value)){
            password = value
        }else{
            throw InvalidPasswordException("$value is Invalid as Password")
        }
    }

    fun setUserEmail(value:String) {
        if(Validator.validEmail(value)) {
            email = value
        }else{
            throw InvalidEmailException("$value is Invalid as Email Address")
        }
    }

    fun setUserNumber(value:String) {
        if(Validator.validPhoneNumber(value)){
            number= value
        }else{
            throw InvalidPhoneNumber("$value is invalid as phone number")
        }
    }

}