package com.example.trabajopracticotallerdediseo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            // Acción a realizar cuando se hace clic en el botón
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}