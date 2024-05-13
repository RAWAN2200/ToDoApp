package com.example.todoapp.screens.auth

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.navigation.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth
    var isSignedIn =
        if (auth.currentUser == null) mutableStateOf(Screens.Authentication.rout) else mutableStateOf(
            Screens.MainApp.rout
        )

    val error = mutableStateOf("")

    init {
        auth.apply{
            this.addAuthStateListener {
                isSignedIn.value =
                    if (it.currentUser == null) Screens.Authentication.rout else Screens.MainApp.rout
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                isSignedIn.value = Screens.MainApp.route
            } else {
                error.value = task.exception?.message.orEmpty()
            }
        }
    }

    fun logout(context: Context){
        auth.signOut()
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        ).signOut()
    }

    fun signup(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
//                isSignedIn.value = Screens.MainApp.route
                } else {
                    error.value = task.exception?.message.orEmpty()
                }
            }
    }

    fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
//                error.value = "Email sent"
            } else {
                error.value = task.exception?.message.orEmpty()
            }
        }
    }

}

