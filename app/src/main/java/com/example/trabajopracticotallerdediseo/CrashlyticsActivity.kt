package com.example.trabajopracticotallerdediseo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

class CrashlyticsActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crashlytics_activity)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val crashButton: Button = findViewById(R.id.crash_button)
        val anrButton: Button = findViewById(R.id.anr_button)
        val backButton: Button = findViewById(R.id.crashlytics_activity_back_button)
        crashButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(R.string.boton_clickeado.toString()) {
                param(FirebaseAnalytics.Param.ITEM_ID, "anr_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton de Crash Clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }

            // Causar un crash
            throw NullPointerException("Crash de prueba para Crashlytics")
        }

        anrButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(R.string.boton_clickeado.toString()) {
                param(FirebaseAnalytics.Param.ITEM_ID, "anr_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton de ANR Clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }

            // Causar un ANR
            Thread.sleep(10000)
        }

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
