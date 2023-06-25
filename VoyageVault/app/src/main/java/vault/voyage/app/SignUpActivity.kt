package vault.voyage.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        val firstname = findViewById<EditText>(R.id.firstname_input)
        val lastname = findViewById<EditText>(R.id.lastname_input)
        val username = findViewById<EditText>(R.id.username_input)
        val password = findViewById<EditText>(R.id.password_input)
        val email = findViewById<EditText>(R.id.email_input)
        val phoneNumber = findViewById<EditText>(R.id.phonenumber_input)
        val signupBtn = findViewById<EditText>(R.id.signup_button)

        signupBtn.setOnClickListener {
            val firstName_str = firstname.text.toString()
            val lastName_str = lastname.text.toString()
            val userName_str = username.text.toString()
            val password_str = password.text.toString()
            val email_str = email.text.toString()
            val phoneNumber_str = phoneNumber.text.toString()

        }


    }
}