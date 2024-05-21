package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

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

import com.example.todoapp.ui.theme.ToDoAppTheme

import com.google.firebase.FirebaseApp

import dagger.hilt.android.AndroidEntryPoint


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
                    Screens.MainApp.Profile.rout -> true
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