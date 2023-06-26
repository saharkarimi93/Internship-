package vault.voyage.app

import android.util.Log
import vault.voyage.app.exceptions.EmptyFieldsException
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
        if(username.isEmpty() || firstname.isEmpty()||lastname.isEmpty()||email.isEmpty()||password.isEmpty()||number.isEmpty()){
            throw EmptyFieldsException("Some Fields Are Empty While Registering")
        }
        if(searchUser(username)!=null){
            Log.d("REGISTER FAILED",searchUser(username)!!.username)
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
        for(i in usersList){
            if (i.username.equals(username, ignoreCase = true)) {
                Log.d("Search USER LOG:", "user is available: ${i.username}")
                return i
            }
        }
        return null
    }

    fun login(username:String, password:String): User? {
        if(searchUser(username) !=null){
            var user = searchUser(username)!!
            Log.d("LOGGED IN USERNAME:","${user.username} , ${user.password}")
            Log.d("LOGIN VALUES:","${username} , ${password}")
            Log.d("PASSWORD EQUALITY:","${user.password==password}")
            if(user.password == password){
                return user
            }else{
                throw LoginFailedException("Password is Wrong")
            }
        }else{
            throw LoginFailedException("User Doesn't Exist")
        }
    }
}