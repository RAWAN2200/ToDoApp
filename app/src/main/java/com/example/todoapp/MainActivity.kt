package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.todoapp.component.BottomBar
import com.example.todoapp.navigation.EventsAppNavigation
import com.example.todoapp.navigation.Screens
import com.example.todoapp.screens.auth.AuthViewModel
import com.example.todoapp.screens.auth.SplashScreen
import com.example.todoapp.screens.auth.SplashScreen
import com.example.todoapp.ui.theme.ToDoAppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            val authViewModel: AuthViewModel = hiltViewModel()
            ToDoAppTheme {

                val navController = rememberNavController()

                var showBottomBar by rememberSaveable { mutableStateOf(false) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                showBottomBar = when (navBackStackEntry?.destination?.route) {
                    Screens.MainApp.Home.rout -> true
                    Screens.MainApp.AddScreen.rout -> true
                    Screens.MainApp.TaskByDate.rout -> true
                    Screens.MainApp.CategoryScreen.rout -> true
                    Screens.MainApp.StaticScreen.rout -> true
                    else -> false
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            contentDescription = "MyScreen"
                        },
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {

                        if (authViewModel.error.value.isNotEmpty()) {
                            Snackbar(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                containerColor = Color.Red.copy(0.5f)
                            ) {
                                Text(
                                    text = authViewModel.error.value
                                )
                            }
                        }
                        EventsAppNavigation(authViewModel,navController)
                    }
                    if(showBottomBar) {
                        BottomBar(navController)
                    }
                }
            }
        }
    }
}

// ---------------------------------------------






































//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        Firebase.initializaApp(this)
//        setContent {
//            val authViewModel: AuthViewModel = hiltViewModel()
//            ToDoAppTheme {
//
//                Column {
//
//                    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
//                    val launcher = rememberFirebaseAuthLauncher(
//                        onAuthComplete = { result ->
//                            user = result.user
//                        },
//                        onAuthoError = {
//                            user = null
//                            println(it.message)
//                        }
//                    )
//                    val token = "77542745482-ifgg7ugqa5uepuhrstlfcvfm821t8l3m.apps.googleusercontent.com"
//                    val context = LocalContext.current
//                    Column {
//                        if (user == null ) {
//                            Text(text = "Not Logged In ")
//                            Button(onClick = {
//                                val gso =
//                                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                                        .requestIdToken(token)
//                                        .requestEmail()
//                                        .build()
//                                val googleSignInClient = GoogleSignIn.getClient(context, gso)
//                                launcher.launch(googleSignInClient.signInIntent)
//                            }) {
//                                Text(text = "Sign in via Google")
//                            }
//                        }else{
//                            Text(text = "Welcome ${user?.displayName}")
//                            AsyncImage(
//                                model = user?.photoUrl,
//                                contentDescription = null,
//                                Modifier
//                                    .clip(CircleShape)
//                                    .size(45.dp)
//                            )
//                            Button(onClick = {
//                                Firebase.auth.signOut()
//                                user = null
//                            }) {
//                                Text(text = "SignOut")
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun rememberFirebaseAuthLauncher(
//    onAuthComplete: (AuthResult) -> Unit,
//    onAuthoError: (ApiException) -> Unit
//) : ManagedActivityResultLauncher<Intent, ActivityResult>{
//    val scop = rememberCoroutineScope()
//    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
//        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//        try {
//            val account = task.getResult(ApiException::class.java)!!
//            val credential = GoogleAuthProvider.getCredential(account.idToken!! , null)
//            scop.launch {
//                val authResult = Firebase.auth.signInWithCredential(credential).await()
//                onAuthComplete(authResult)
//            }
//        }catch (e: ApiException) {
//            onAuthoError
//        }
//    }
//}