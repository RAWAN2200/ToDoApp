package com.example.todoapp.component

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.todoapp.R
import com.example.todoapp.navigation.Screens
import com.example.todoapp.screens.auth.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.BuildConfig
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun LoginWithGoogle(authViewModel: AuthViewModel) {
    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val luncher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
//            authViewModel.isSignedIn.value = Screens.MainApp.route
        },
        onAuthError = {
            user = null
//            authViewModel.isSignedIn.value = Screens.Authentication.route
        }
    )

    val context = LocalContext.current

    if (user == null) {

        Image(
            modifier = Modifier.padding(4.dp)
                .clickable {
                    val gso =
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(com.example.todoapp.BuildConfig.token).requestEmail()
                            .build()
                    val googleSignInClient =
                        GoogleSignIn.getClient(context, gso)
                    luncher.launch(googleSignInClient.signInIntent)
                },
            painter = painterResource(id = R.drawable.google_image),
            contentDescription = "google"
        )
    }
}

@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (ApiException) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {

    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
                onAuthComplete(authResult)
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }
    }

}