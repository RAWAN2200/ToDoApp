package com.example.todoapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val rout: String) {
    data object MainApp : Screens("mainGraph") {
        data object Home : Screens("home_screen")
        data object TaskByDate : Screens("task_by_date_screen")
        data object AddScreen : Screens("Add_screen")
        data object CategoryScreen : Screens("category_screen")
        data object StaticScreen : Screens("Statics_Screen")
    }

    data object Authentication : Screens("authGraph") {
        data object Splash : Screens("splash")
        data object SignUp : Screens("signup_rout")
        data object Login : Screens("login_rout")
    }
}

 data class BoottomNavigationItem(
     val icon : ImageVector = Icons.Filled.Home,
     val rout : String = ""
 ){
     fun bottomNavigationItems(): List<BoottomNavigationItem>{
         return listOf(
             BoottomNavigationItem(
                 icon = Icons.Outlined.Home,
                 rout = Screens.MainApp.Home.rout
             ),
             BoottomNavigationItem(
                 icon = Icons.Outlined.List,
                 rout = Screens.MainApp.TaskByDate.rout
             ),
             BoottomNavigationItem(
                 icon = Icons.Outlined.AddCircle ,
                 rout = Screens.MainApp.AddScreen.rout
             ),
             BoottomNavigationItem(
                 icon = Icons.Outlined.Settings,
                 rout = Screens.MainApp.CategoryScreen.rout
             ),
             BoottomNavigationItem(
                 icon = Icons.Outlined.DateRange,
                 rout = Screens.MainApp.StaticScreen.rout
             ),
             )

     }
 }
