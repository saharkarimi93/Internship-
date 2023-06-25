package vault.voyage.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import vault.voyage.app.exceptions.LoginFailedException

class LoginActivity : AppCompatActivity() {
    var users = UsersControl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        //TEST
        users.register("sahar","sahar","karimi","saharkarimi@gmail.com","12345","+123456789")


        val travelImage = findViewById<ImageView>(R.id.firstImage)
        travelImage.setImageResource(R.drawable.travel)
        val login:Button = findViewById(R.id.login_button)
        val username:EditText = findViewById(R.id.username_login)
        val password:EditText = findViewById(R.id.password_login)
        login.setOnClickListener{
            var username_input = username.text.toString()
            var password_input = password.text.toString()
            try{
                val loggedInUser = users.login(username_input,password_input)!!
                Log.d("LOGIN TEST LOG:", (loggedInUser==null).toString())
                PanelActivity.user = loggedInUser
                val panelActivity = Intent(this,PanelActivity::class.java)

                startActivity(panelActivity)
            }catch (ex:LoginFailedException){
                throw ex
            }

        }
        val button:Button = findViewById(R.id.signup_button);
        button.setOnClickListener {
            val signUpActivity = Intent(this,SignUpActivity::class.java)
            startActivity(signUpActivity)
        }
    }
}