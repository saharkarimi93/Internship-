package vault.voyage.app

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.EmptyFieldsException
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.LoginFailedException
import vault.voyage.app.exceptions.UserExistException
import vault.voyage.app.model.User
//TODO Fix This ... Error about Db and context
class UsersControl(val db:AppDatabase) {
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

        var registeredUser = User()
        try{
            registeredUser.setUserEmail(email)
            registeredUser.setUserNumber(number)

            registeredUser.username = username
            registeredUser.firstname = firstname
            registeredUser.lastname= lastname
            registeredUser.password = password

            if(userExist(username)){
                throw UserExistException("User Already Exist")
            }else {
                CoroutineScope(Dispatchers.IO).launch {
                    db.users.updateUser(registeredUser)
                    Log.d("REGISTER LOG:", "$username Registered. Size: ${db.users.usersAmount()}")
                }
            }
        }catch (ex:Exception){
            when(ex){
                is InvalidPhoneNumber-> throw InvalidPhoneNumber("Invalid Phone Number")
                is InvalidEmailException ->throw InvalidEmailException("Invalid Email")
                else-> throw ex
            }
        }
    }

    private fun userExist(username:String): Boolean {
        var b = false
        CoroutineScope(Dispatchers.IO).launch {
            b = db.users.countUser(username) == 1
        }
        return b
    }

    fun login(username:String, password:String): User? {
        var user:User? = null
        CoroutineScope(Dispatchers.IO).launch {
            if (userExist(username)) {
                user = db.users.loginUser(username, password)
            } else {
                throw LoginFailedException("User doesn't exist")
            }
        }
        return user
    }
}