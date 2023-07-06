package vault.voyage.app

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.EmptyFieldsException
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.InvalidPhoneNumber
import vault.voyage.app.exceptions.RegisterFailedException

class SignUpActivity : AppCompatActivity() {
    private lateinit var layout: ConstraintLayout
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
                UsersControl(db).register(
                    userName_str,
                    firstName_str,
                    lastName_str,
                    email_str,
                    password_str,
                    phoneNumber_str
                )
                makeSnackBar("User Registered Successfully")
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
}