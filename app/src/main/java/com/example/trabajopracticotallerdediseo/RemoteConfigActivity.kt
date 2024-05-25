package com.example.trabajopracticotallerdediseo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class RemoteConfigActivity : AppCompatActivity() {

    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remote_config_activity)

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        // Valores por default por si no puede traer los valores de firebase
        val configDefaults = mapOf(
            "button_one_visibility" to true,
            "button_two_visibility" to true
        )
        firebaseRemoteConfig.setDefaultsAsync(configDefaults)

        // Configura las opciones de actualizacion de Remote Config
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(60) // 1 min de actualizacion
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)

        // Trae los valores definidos en la consola de Firebase y los activa
        fetchAndActivateConfig()

        val buttonOne: Button = findViewById(R.id.remote_config_button_one)
        val buttonTwo: Button = findViewById(R.id.remote_config_button_two)
        val backButton: Button = findViewById(R.id.remote_config_activity_back_button)

        buttonOne.setOnClickListener {
            Toast.makeText(this, "Botón 1", Toast.LENGTH_SHORT).show()
        }

        buttonTwo.setOnClickListener {
            Toast.makeText(this, "Botón 2", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // usar un dispositivo con Play Store para que funcione esto
    private fun fetchAndActivateConfig() {
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    if (updated) {
                        Log.i("RemoteConfigActivity", "Remote config values were updated")
                    } else {
                        Log.i("RemoteConfigActivity", "Remote config values were already up-to-date")
                    }

                    // Trae los valores que se definieron en la consola de firebase para estos parametros
                    val buttonOneVisibility = firebaseRemoteConfig.getBoolean("button_one_visibility")
                    val buttonTwoVisibility = firebaseRemoteConfig.getBoolean("button_two_visibility")

                    // Aplica la visibilidad a los botones segun el valor
                    findViewById<Button>(R.id.remote_config_button_one).visibility =
                    if (buttonOneVisibility) View.VISIBLE else View.GONE
                    findViewById<Button>(R.id.remote_config_button_two).visibility =
                        if (buttonTwoVisibility) View.VISIBLE else View.GONE
                } else {
                    Log.e("RemoteConfigActivity", "Error fetching and activating config", task.exception)
                }
            }
    }
}
