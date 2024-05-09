package com.example.trabajopracticotallerdediseo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrashlyticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crashlytics_activity)

        val mainButton: Button = findViewById(R.id.main_button)
        val backButton: Button = findViewById(R.id.back_button)
        mainButton.setOnClickListener {
            Toast.makeText(this, "CrashlyticsActivity", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}