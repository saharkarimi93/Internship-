package vault.voyage.app

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)
        val image = findViewById<ImageView>(R.id.emptyImage)
        image.setImageResource(R.drawable.nothing_found)
    }
}