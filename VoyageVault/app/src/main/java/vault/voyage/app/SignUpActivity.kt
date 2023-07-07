package vault.voyage.app

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.EmptyFieldsException
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.RegisterFailedException
import vault.voyage.app.model.User

class SignUpActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
    private lateinit var firstname:EditText
    private lateinit var lastname :EditText
    private lateinit var username :EditText
    private lateinit var password :EditText
    private lateinit var email :EditText
    private lateinit var phoneNumber :EditText
    private lateinit var signupBtn :Button

    private var db: AppDatabase = LoginActivity.db

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        initializeUi()

        signupBtn.setOnClickListener {
            val firstName_str = firstname.text.toString()
            val lastName_str = lastname.text.toString()
            val userName_str = username.text.toString()
            val password_str = password.text.toString()
            val email_str = email.text.toString()
            val phoneNumber_str = phoneNumber.text.toString()


            try {
                fieldsValidation(userName_str, firstName_str, lastName_str, email_str, password_str, phoneNumber_str)

                var registeredUser = createUser(email_str, phoneNumber_str, userName_str, firstName_str, lastName_str, password_str
                )
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d("in Scope_count:", "${db.users.countUser(userName_str)}")
                    signUpUser(registeredUser)
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
    fun initializeUi(){
        layout = findViewById(R.id.signup_constraintLayout)
        firstname = findViewById<EditText>(R.id.signup_firstname)
        lastname = findViewById<EditText>(R.id.signup_lastname)
        username = findViewById<EditText>(R.id.signup_username)
        password = findViewById<EditText>(R.id.signup_password)
        email = findViewById<EditText>(R.id.signup_email)
        phoneNumber = findViewById<EditText>(R.id.signup_phonenumber)
        signupBtn = findViewById<Button>(R.id.signup_button)
    }
    suspend fun signUpUser(user:User){
        if(db.users.countUser(user.username)==1){
            runOnUiThread{
                makeSnackBar("User Exists Already")
            }
        }else{
            db.users.updateUser(user)
            makeSnackBar("User Registered Successfully")
            Log.d("REGISTER LOG:", "${user.username} Registered. Size: ${db.users.usersAmount()}")
        }
    }

    private fun createUser(
        email_str: String,
        phoneNumber_str: String,
        userName_str: String,
        firstName_str: String,
        lastName_str: String,
        password_str: String
    ):User {
        val registeredUser = User()
        registeredUser.setUserEmail(email_str)
        registeredUser.setUserNumber(phoneNumber_str)
        registeredUser.username = userName_str
        registeredUser.firstname = firstName_str
        registeredUser.lastname = lastName_str
        registeredUser.password = password_str
        return registeredUser
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