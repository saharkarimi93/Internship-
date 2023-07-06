package vault.voyage.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room.databaseBuilder
import com.google.android.material.snackbar.Snackbar
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.LoginFailedException
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private var layout:ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val db = databaseBuilder(applicationContext, AppDatabase::class.java, "voyage-vault.db").build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        layout = findViewById(R.id.login_constraintLayout)
        val users = UsersControl(db)

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
                val loggedInUser = users.login(username_input,password_input)!!//(username_input,password_input)!!
                Log.d("LOGIN TEST LOG:", (loggedInUser==null).toString())
                PanelActivity.user = loggedInUser // loggedInUser
                val panelActivity = Intent(this,PanelActivity::class.java)

                startActivity(panelActivity)
            }catch (ex:Exception){
                when(ex){
                    is LoginFailedException->{
                        var snack = Snackbar.make(layout!!,"Login Failed. Try Again",Snackbar.LENGTH_SHORT)
                        snack.setAction("OK"){
                        }
                        snack.show()
                    }
                }
            }

        }
        val button:Button = findViewById(R.id.signup_button);
        button.setOnClickListener {
            val signUpActivity = Intent(this,SignUpActivity::class.java)
            startActivity(signUpActivity)
        }
    }
}