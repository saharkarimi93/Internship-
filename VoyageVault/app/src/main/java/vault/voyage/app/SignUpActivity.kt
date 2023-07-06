package vault.voyage.app

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.EmptyFieldsException
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.RegisterFailedException
import vault.voyage.app.model.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        layout = findViewById(R.id.signup_constraintLayout)
        val firstname = findViewById<EditText>(R.id.signup_firstname)
        val lastname = findViewById<EditText>(R.id.signup_lastname)
        val username = findViewById<EditText>(R.id.signup_username)
        val password = findViewById<EditText>(R.id.signup_password)
        val email = findViewById<EditText>(R.id.signup_email)
        val phoneNumber = findViewById<EditText>(R.id.signup_phonenumber)
        val signupBtn = findViewById<Button>(R.id.signup_button)

        signupBtn.setOnClickListener {
            val firstName_str = firstname.text.toString()
            val lastName_str = lastname.text.toString()
            val userName_str = username.text.toString()
            val password_str = password.text.toString()
            val email_str = email.text.toString()
            val phoneNumber_str = phoneNumber.text.toString()
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "voyage-vault.db"
            ).build()

            try {
                fieldsValidation(
                    userName_str,
                    firstName_str,
                    lastName_str,
                    email_str,
                    password_str,
                    phoneNumber_str
                )
                var registeredUser = User()
                try{
                    registeredUser.setUserEmail(email_str)
                    registeredUser.setUserNumber(phoneNumber_str)
                    registeredUser.username = userName_str
                    registeredUser.firstname = firstName_str
                    registeredUser.lastname= lastName_str
                    registeredUser.password = password_str
                    CoroutineScope(Dispatchers.IO).launch {
                        Log.d("in Scope_count:", "${db.users.countUser(userName_str)}")
                        if(db.users.countUser(userName_str)==1){
                            runOnUiThread{
                                makeSnackBar("User Exists Already")
                            }
                        }else{
                            db.users.updateUser(registeredUser)
                            makeSnackBar("User Registered Successfully")
                            Log.d("REGISTER LOG:", "$username Registered. Size: ${db.users.usersAmount()}")
                        }
                    }

                }catch (ex:Exception){
                    runOnUiThread {
                        when(ex){
                            is InvalidPhoneNumber-> throw InvalidPhoneNumber("Invalid Phone Number")
                            is InvalidEmailException ->throw InvalidEmailException("Invalid Email")
                            else-> throw ex
                        }
                    }
                }

            }catch (ex:Exception){
                when(ex){
                    is RegisterFailedException->{
                        makeSnackBar("Register Failed. User Exists Already")
                    }
                    is InvalidEmailException->{
                        makeDialog("Email is Invalid. Valid Form: example@example.com", )
                    }
                    is InvalidPhoneNumber->{
                        makeDialog("Number is Invalid. Valid Forms: +999999999999 or Any number between 7 to 14 character")
                    }
                    is EmptyFieldsException->{
                        makeSnackBar("Some Fields Are Empty")
                    }
                }
            }

        }
    }
    fun makeSnackBar(message:String){
        var snack = Snackbar.make(layout!!,message, Snackbar.LENGTH_SHORT)
        snack.setAction("OK"){
        }
        snack.show()
    }
    private fun makeDialog(dialogText: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(dialogText)
        builder.setTitle("Error")
        builder.setNegativeButton("Ok") { dialog, which ->
            dialog.cancel()
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun fieldsValidation(
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


    }

}