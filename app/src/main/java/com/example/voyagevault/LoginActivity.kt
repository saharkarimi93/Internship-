package vault.voyage.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        val travelImage = findViewById<ImageView>(R.id.firstImage)
        travelImage.setImageResource(R.drawable.travel)

        val button:Button = findViewById(R.id.signup_button);
        button.setOnClickListener {
            val signUpActivity = Intent(this,SignUpActivity::class.java)
            startActivity(signUpActivity)
        }
    }
}