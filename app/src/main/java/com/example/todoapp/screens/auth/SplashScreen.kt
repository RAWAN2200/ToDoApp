package com.example.todoapp.screens.auth

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screens


@Composable
fun SplashScreen (navController: NavController){
Column() {
    Image(painter = painterResource(id = R.drawable.todologo), contentDescription = "to do logo",
        modifier = Modifier.padding(start = 60.dp, top = 150.dp )

    )
    Text(text = "ToDoApp",
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xff5B67CA),
        modifier = Modifier.padding(start = 120.dp, top = 100.dp )
    )
    Text(text = "Plan what you will do to be more organized for ",
        fontSize = 16.sp,
      modifier = Modifier.padding(start = 40.dp, top = 50.dp )
    )
    Text(text = " today, tomorrow and beyond",
        fontSize = 17.sp,
        modifier = Modifier.padding(start = 100.dp, top = 5.dp )
    )
    Button(
        onClick = {  navController.navigate(Screens.Authentication.Login.rout)},
        modifier = Modifier
            .padding(start = 40.dp, top = 40.dp)
            .width(300.dp),
        colors = ButtonDefaults.buttonColors( Color(0xff5B67CA)), // تعيين اللون
        shape = RoundedCornerShape(10.dp) //
        ) {
        Text(text = "Login")
    }
    Text(text = "Sign Up",
        color = Color(0xff5B67CA),
        fontSize = 17.sp,
        modifier = Modifier.padding(start = 160.dp, top = 5.dp ).clickable { navController.navigate(Screens.Authentication.SignUp.rout)}
    )

}
    Row{
        Text(text = ".",
            fontSize = 45.sp,
            color = Color(0xffFD7694),
            modifier = Modifier.padding(start = 250.dp, top = 525.dp )
        )
    }
}