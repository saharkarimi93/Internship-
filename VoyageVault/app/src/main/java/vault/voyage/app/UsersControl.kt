package vault.voyage.app

import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.RegisterFailedException
import vault.voyage.app.model.User

class UsersControl {
    var usersList = mutableListOf<User>()

    fun register(
         username:String,
         firstname:String,
         lastname:String,
         email:String,
         password:String,
         number:String
    ){
        var registeredUser = User()
        try{
            registeredUser.username = username
            registeredUser.firstname = firstname
            registeredUser.lastname= lastname
            registeredUser.setUserEmail(email)
            registeredUser.password = password
            registeredUser.setUserNumber(number)
        }catch (ex:Exception){
            when(ex){
                is InvalidPhoneNumber, is InvalidEmailException->{
                    throw  RegisterFailedException("Register Failed")
                }
                else-> throw ex
            }
        }
    }
}