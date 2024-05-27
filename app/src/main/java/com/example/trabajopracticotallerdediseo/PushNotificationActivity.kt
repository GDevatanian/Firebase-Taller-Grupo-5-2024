package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PushNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.push_notification_activity)

        val backButton: Button = findViewById(R.id.push_notification_activity_back_button)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK // Limpia todas las activities que estan en el stack de retrocesos
            startActivity(intent)
            finish() // Termina al activity para que no quede en el stack
        }
    }
}
