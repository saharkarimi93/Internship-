package vault.voyage.app.panelFragments.todoFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import vault.voyage.app.R

class TaskFullInformation() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_full_information)
        val desc = findViewById<TextView>(R.id.task_des_full)
        desc.text = intent.getStringExtra("DESCRIPTION")
    }
}