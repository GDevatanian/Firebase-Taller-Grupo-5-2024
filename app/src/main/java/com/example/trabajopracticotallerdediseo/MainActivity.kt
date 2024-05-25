package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val crashlyticsButton: Button = findViewById(R.id.crashlytics_button)
        val remoteConfigButton: Button = findViewById(R.id.remote_config_button)
        crashlyticsButton.setOnClickListener {
            val intent = Intent(this, CrashlyticsActivity::class.java)
            startActivity(intent)
        }
        remoteConfigButton.setOnClickListener {
            val intent = Intent(this, RemoteConfigActivity::class.java)
            startActivity(intent)
        }
    }
}
