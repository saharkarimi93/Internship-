package vault.voyage.app

import android.util.Log
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.LoginFailedException
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
        if(searchUser(username)!=null){
            throw RegisterFailedException("Register Failed. User Exists Already")
        }
        var registeredUser = User()
        try{
            registeredUser.setUserEmail(email)
            registeredUser.setUserNumber(number)

            registeredUser.username = username
            registeredUser.firstname = firstname
            registeredUser.lastname= lastname
            registeredUser.password = password
            usersList.add(registeredUser)
            Log.d("REGISTER LOG:","$username Registered. Size: ${usersList.size}")
        }catch (ex:Exception){
            when(ex){
                is InvalidPhoneNumber-> throw InvalidPhoneNumber("Invalid Phone Number")
                is InvalidEmailException ->throw InvalidEmailException("Invalid Email")
                else-> throw ex
            }
        }
    }

    private fun searchUser(username:String): User? {
        usersList.forEach{
                e-> run {
            if (e.username.equals(username, ignoreCase = true))
                Log.d("Search USER LOG:","user is available")
            return e
        }
        }
        return null
    }

    fun login(username:String, password:String): User? {
        if(searchUser(username) !=null){
            var user = searchUser(username)!!
            if(user.password.equals(password,ignoreCase = false)){
                return user
            }else{
                throw LoginFailedException("Password is Wrong")
            }
        }else{
            throw LoginFailedException("User Doesn't Exist")
        }
    }
}