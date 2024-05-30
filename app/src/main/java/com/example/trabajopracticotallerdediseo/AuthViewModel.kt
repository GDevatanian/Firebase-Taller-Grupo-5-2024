package com.example.trabajopracticotallerdediseo

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signInWithEmailAndPassword(email: String, password: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { e -> onFailure.invoke(e.message ?: "Authentication failed.") }
    }

    fun signUpWithEmailAndPassword(email: String, password: String,
                                   onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                auth.currentUser?.let {
                    onSuccess.invoke()
                }
            }
            .addOnFailureListener { e ->
                val errorMessage = e.message ?: "Registration failed."
                Log.e("AuthViewModel", "Error creating user: $errorMessage")
                onFailure.invoke(errorMessage)
            }
    }

    fun getUserFirstName(userId: String?, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        if (userId == null) {
            onSuccess.invoke("Anónimo") // Se muestra "Anónimo" si el userId es nulo
            return
        }

        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val firstName = document.getString("firstName")
                    if (firstName != null) {
                        onSuccess.invoke(firstName)
                    } else {
                        onFailure.invoke("No se pudo obtener el nombre del usuario.")
                    }
                } else {
                    onFailure.invoke("No se encontró el documento del usuario.")
                }
            }
            .addOnFailureListener { e ->
                onFailure.invoke(e.message ?: "Error al obtener el nombre del usuario.")
            }
    }

    fun addUserInfo(userId: String, firstName: String, lastName: String,
                    onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val user = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName
        )

        db.collection("users")
            .document(userId)
            .set(user)
            .addOnSuccessListener { onSuccess.invoke() }
            .addOnFailureListener { e -> onFailure.invoke(e.message ?: "Failed to add user info.") }
    }
}
