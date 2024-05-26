package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val crashlyticsButton: Button = findViewById(R.id.crashlytics_button)
        val remoteConfigButton: Button = findViewById(R.id.remote_config_button)
        crashlyticsButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
                param(FirebaseAnalytics.Param.ITEM_ID, "crashlytics_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton para ir a la pantalla de Crashlytics clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }
            val intent = Intent(this, CrashlyticsActivity::class.java)
            startActivity(intent)
        }
        remoteConfigButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
                param(FirebaseAnalytics.Param.ITEM_ID, "remote_config_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton para ir a la pantalla de Remote Config clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }
            val intent = Intent(this, RemoteConfigActivity::class.java)
            startActivity(intent)
        }
    }
}
