package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        val mainActivityTitle: TextView = findViewById(R.id.main_activity_title)
        val crashlyticsButton: Button = findViewById(R.id.crashlytics_button)
        val remoteConfigButton: Button = findViewById(R.id.remote_config_button)
        val logoutButton: Button = findViewById(R.id.logout_main_activity_button)
        showCustomWelcome(mainActivityTitle, viewModel)

        crashlyticsButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(Constants.BOTON_CLICKEADO) {
                param(FirebaseAnalytics.Param.ITEM_ID, "crashlytics_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton para ir a la pantalla de Crashlytics clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }
            val intent = Intent(this, CrashlyticsActivity::class.java)
            startActivity(intent)
        }
        remoteConfigButton.setOnClickListener {
            // Logear evento de analytics
            firebaseAnalytics.logEvent(Constants.BOTON_CLICKEADO) {
                param(FirebaseAnalytics.Param.ITEM_ID, "remote_config_button")
                param(FirebaseAnalytics.Param.ITEM_NAME, "Boton para ir a la pantalla de Remote Config clickeado")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")
            }
            val intent = Intent(this, RemoteConfigActivity::class.java)
            startActivity(intent)
        }
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // se limpia el stack de pantallas
            startActivity(intent)
        }
    }

    fun showCustomWelcome(mainActivityTitle: TextView, viewModel: AuthViewModel) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            viewModel.getUserFirstName(it,
                onSuccess = { nombreUsuario ->
                    val welcomeText = "Bienvenido $nombreUsuario"
                    mainActivityTitle.text = welcomeText
                },
                onFailure = { error ->
                    mainActivityTitle.text = "Bienvenido Anónimo"
                }
            )
        }
    }
}
