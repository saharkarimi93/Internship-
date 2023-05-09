package vault.voyage.app

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var travelImage = findViewById<ImageView>(R.id.firstImage)
        travelImage.setImageResource(R.drawable.travel)
    }
}