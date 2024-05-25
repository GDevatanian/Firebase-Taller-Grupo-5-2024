package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val mainButton: Button = findViewById(R.id.crashlytics_button)
        mainButton.setOnClickListener {
            val intent = Intent(this, CrashlyticsActivity::class.java)
            startActivity(intent)
        }
    }
}
