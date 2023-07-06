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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.InvalidEmailException
import vault.voyage.app.exceptions.LoginFailedException
import vault.voyage.app.model.User
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    lateinit var db:AppDatabase

    private var layout:ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        layout = findViewById(R.id.login_constraintLayout)
        db = databaseBuilder(applicationContext, AppDatabase::class.java, "voyage-vault.db").build()
//        val users = UsersControl(db)
        //TEST


        val travelImage = findViewById<ImageView>(R.id.firstImage)
        travelImage.setImageResource(R.drawable.travel)
        val login:Button = findViewById(R.id.login_button)
        val username:EditText = findViewById(R.id.username_login)
        val password:EditText = findViewById(R.id.password_login)
        login.setOnClickListener{
            var username_input = username.text.toString()
            var password_input = password.text.toString()
            try{
                CoroutineScope(Dispatchers.IO).launch {
                    val loggedInUser = login(username_input,password_input)//(username_input,password_input)!!
                    if(loggedInUser!=null)
                        PanelActivity.user = loggedInUser // loggedInUser
                    else{
                        runOnUiThread {
                            var snack = Snackbar.make(layout!!,"Login Failed. Try Again",Snackbar.LENGTH_SHORT)
                            snack.setAction("OK"){
                            }
                            snack.show()
                        }
                    }
                    Log.d("LOGIN TEST LOG:", (loggedInUser==null).toString())
                }
                if(PanelActivity.user!=null) {
                    Log.d("Start Panel:","Starting...")
                    Intent(this, PanelActivity::class.java).also {startActivity(it)}
                }
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
    suspend fun login(username:String, password:String): User? {
        if (db.users.countUser(username) == 1) {
            val user = db.users.loginUser(username, password)
            if (user.isEmpty()) {
                runOnUiThread{
                    var snack = Snackbar.make(layout!!,"Login Failed. Try Again",Snackbar.LENGTH_SHORT)
                    snack.setAction("OK"){
                    }
                    snack.show()
                }
            } else {
                return user[0]
            }
        }
        return null
    }
}