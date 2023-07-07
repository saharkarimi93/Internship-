package vault.voyage.app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room.databaseBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vault.voyage.app.database.AppDatabase
import vault.voyage.app.exceptions.LoginFailedException
import vault.voyage.app.model.Task
import vault.voyage.app.model.User
import kotlin.streams.toList

class LoginActivity : AppCompatActivity() {
    companion object{
        @JvmStatic lateinit var db:AppDatabase
    }

    private var layout:ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        layout = findViewById(R.id.login_constraintLayout)
        db = databaseBuilder(applicationContext, AppDatabase::class.java, "voyage-vault.db").build()

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
                    login(username_input,password_input)
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
    private fun goToPanel(user:User){
        Log.d("Start Panel:","Starting...")
        PanelActivity.user = user
        Intent(this, PanelActivity::class.java).also {startActivity(it)}
    }
    suspend fun login(username:String, password:String){
        if (db.users.countUser(username) == 1) {
            val user = db.users.loginUser(username, password)

            if (user.isEmpty()) {
                loginFailedError()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    user[0].todoList.undoneTasks= db.tasks.getTasks(user[0].username).stream().filter { t-> !t.done }.toList() as ArrayList<Task>
                    user[0].todoList.doneTasks= db.tasks.getTasks(user[0].username).stream().filter { t-> t.done }.toList() as ArrayList<Task>
                }
                goToPanel(user[0])

            }
        }else{
            loginFailedError()
        }
    }

    private fun loginFailedError(){
        runOnUiThread{
            var snack = Snackbar.make(layout!!,"Login Failed. Try Again",Snackbar.LENGTH_SHORT)
            snack.setAction("OK"){
            }
            snack.show()
        }
    }
}