package com.example.trabajopracticotallerdediseo

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.login_activity)

            auth = FirebaseAuth.getInstance()
            viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)


            val signInButton: Button = findViewById(R.id.sign_in_button)
            val signUpButton: Button = findViewById(R.id.sign_up_button)
            val signInLoginButton: Button = findViewById(R.id.sign_in_login_button)
            val signUpLoginButton: Button = findViewById(R.id.sign_up_login_button)
            val anonymousLoginButton: Button = findViewById(R.id.anonymous_login_button)
            val googleLoginButton: Button = findViewById(R.id.google_login_button)
            val facebookLoginButton: Button = findViewById(R.id.facebook_login_button)
            val signInContainer: LinearLayout = findViewById(R.id.sign_in_container)
            val signUpContainer: LinearLayout = findViewById(R.id.sign_up_container)
            val signInEmailEditText: EditText = findViewById(R.id.sign_in_email_edit_text)
            val signInPasswordEditText: EditText = findViewById(R.id.sign_in_password_edit_text)
            val signUpFirstNameEditText: EditText = findViewById(R.id.sign_up_first_name_edit_text)
            val signUpLastNameEditText: EditText = findViewById(R.id.sign_up_last_name_edit_text)
            val signUpEmailEditText: EditText = findViewById(R.id.sign_up_email_edit_text)
            val signUpPasswordEditText: EditText = findViewById(R.id.sign_up_password_edit_text)
            val alternateLoginText: LinearLayout = findViewById(R.id.alternate_sign_up)

        animarBotonesDeGoogleYFacebook(googleLoginButton, facebookLoginButton)

            signInButton.setOnClickListener {
                signInContainer.visibility = View.VISIBLE
                signUpContainer.visibility = View.INVISIBLE
                signInButton.setBackgroundColor(getColor(R.color.less_black))
                signUpButton.setBackgroundColor(getColor(R.color.gray))
            }

            signUpButton.setOnClickListener {
                signInContainer.visibility = View.INVISIBLE
                signUpContainer.visibility = View.VISIBLE
                signInButton.setBackgroundColor(getColor(R.color.gray))
                signUpButton.setBackgroundColor(getColor(R.color.less_black))
            }

            signInLoginButton.setOnClickListener {
                val email = signInEmailEditText.text.toString().trim()
                val password = signInPasswordEditText.text.toString().trim()

                viewModel.signInWithEmailAndPassword(email, password,
                    onSuccess = {
                        Toast.makeText(this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    },
                    onFailure = { error ->
                        Toast.makeText(
                            this,
                            "Credenciales invalidas, Intente de nuevo",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }

            anonymousLoginButton.setOnClickListener {
                viewModel.signInAnonymously(
                    onSuccess = {
                        Toast.makeText(this, "Inicio de sesion anonimo exitoso", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    },
                    onFailure = { error ->
                        Toast.makeText(this, "Fallo el inicio de sesion: $error", Toast.LENGTH_LONG)
                            .show()
                    })
            }
        
            signUpLoginButton.setOnClickListener {
                val email = signUpEmailEditText.text.toString().trim()
                val password = signUpPasswordEditText.text.toString().trim()
                val firstName = signUpFirstNameEditText.text.toString().trim()
                val lastName = signUpLastNameEditText.text.toString().trim()

                viewModel.signUpWithEmailAndPassword(email, password,
                    onSuccess = {
                        viewModel.addUserInfo(auth.currentUser?.uid ?: "", firstName, lastName,
                            onSuccess = {
                                Toast.makeText(
                                    this,
                                    "Cuenta creada exitosamente.",
                                    Toast.LENGTH_LONG
                                ).show()
                            },
                            onFailure = { error ->
                                Toast.makeText(
                                    this,
                                    "No se pudo cargar la info del usuario: $error",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                        Toast.makeText(this, "Cuenta creada exitosamente.", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    },
                    onFailure = { error ->
                        Toast.makeText(this, "Error al crear la cuenta: $error", Toast.LENGTH_LONG)
                            .show()
                    }
                )
            }

            googleLoginButton.setOnClickListener {
                // Manejar el login de google
            }

            facebookLoginButton.setOnClickListener {
                // Manejar el login de facebook
            }
    }

    fun animarBotonesDeGoogleYFacebook(googleLoginButton:Button, facebookLoginButton:Button){
        val statesGooglebtn = StateListDrawable()
        val defaultColor = ColorDrawable(ContextCompat.getColor(this,(R.color.gray)))
        val pressedColor = ColorDrawable(ContextCompat.getColor(this,(R.color.white)))

        statesGooglebtn.addState(intArrayOf(android.R.attr.state_pressed), pressedColor)
        statesGooglebtn.addState(intArrayOf(), defaultColor)

        googleLoginButton.background = statesGooglebtn

        val statesFacebookbtn = StateListDrawable()

        statesFacebookbtn.addState(intArrayOf(android.R.attr.state_pressed), pressedColor)
        statesFacebookbtn.addState(intArrayOf(), defaultColor)

        facebookLoginButton.background = statesFacebookbtn
    }
}
