package com.example.todoapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.todoapp.component.LoginWithGoogle

import com.example.todoapp.screens.auth.AuthViewModel
import com.example.todoapp.screens.auth.LoginScreen
import com.example.todoapp.screens.auth.SignUpScreen
import com.example.todoapp.screens.auth.SplashScreen

@Composable
fun EventsAppNavigation(
    authViewModel: AuthViewModel,
    navController: NavHostController

){
    val context = LocalContext.current
   NavHost(navController = navController,
       startDestination = authViewModel.isSignedIn.value)
   {
       authNavigation( navController,  authViewModel)
       mainAppNavigation( navController){
          authViewModel.logout(context)
       }
   }
}
fun NavGraphBuilder.authNavigation(
    navController: NavController,
    authViewModel: AuthViewModel
){
    navigation(
        startDestination = Screens.Authentication.Splash.rout ,
        route = Screens.Authentication.rout,
    ){
        composable(Screens.Authentication.Splash.rout){
           SplashScreen(navController)//
        }
        composable(Screens.Authentication.SignUp.rout){
            SignUpScreen(navController , authViewModel)
        }
        composable(Screens.Authentication.Login.rout){
            LoginScreen(navController , authViewModel)
        }


    }
}
fun NavGraphBuilder.mainAppNavigation(
    navController: NavController,
    logout: () -> Unit
) {
    navigation(
        startDestination = Screens.MainApp.Home.rout,
        route = Screens.MainApp.rout
    ) {
        composable(Screens.MainApp.Home.rout) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {

            }
        }
        composable(Screens.MainApp.TaskByDate.rout) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            ) {

            }
        }
        composable(Screens.MainApp.CategoryScreen.rout) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            ) {
                Button(onClick = {
                    logout.invoke()

                })
                {
                    Text(text = "SignedOut")
                }
            }
        }
        composable(Screens.MainApp.AddScreen.rout) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Magenta)
            ) {

            }
        }
        composable(Screens.MainApp.StaticScreen.rout) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) {

            }
        }
    }
}

fun NavOptionsBuilder.popUpToTop(navController: NavController){
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return){
        saveState = true
        inclusive = true
    }

}


//nav for 3 first screen
//@Composable
//fun NavGraph( navController: NavController,
//    authViewModel: AuthViewModel){
//    val navController = rememberNavController()
//    NavHost(navController =navController , startDestination ="splash" ) {
//        composable(route = "splash") {
//            SplashScreen(navController)
//    }
//        composable(route = "login") {
//            LoginScreen(navController , authViewModel)
//        }
//        composable(route = "signUp") {
//            SignUpScreen(navController , authViewModel)
//        }
//        composable(route = "loginWithGoogle") {
//            LoginWithGoogle ( authViewModel)
//        }
//}}