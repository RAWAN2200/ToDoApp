package com.example.todoapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.navigation.Screens


@Composable
fun SignUpScreen(navController: NavController,
                 authViewModel: AuthViewModel)
{
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    Column {
        Text(
            text = "Sign Up",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xff5B67CA),
            modifier = Modifier.padding(start = 30.dp, top = 100.dp)
        )

        Column(modifier = Modifier
            .fillMaxSize() ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name,
                onValueChange = { name= it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "InfolIcon"
                    )
                },
                label = { Text(text = " Username") },
                placeholder = { Text(text = "") },
                colors = TextFieldDefaults.colors(Color.Transparent),


                )
            TextField(
                value = email,
                onValueChange = { email = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "emailIcon"
                    )
                },
                label = { Text(text = "Email ID ") },
                placeholder = { Text(text = "") },
                colors = TextFieldDefaults.colors(Color.Transparent),


                )
            TextField(
                value = pass,
                onValueChange = { pass = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "emailIcon",

                        )
                },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "") },
                colors = TextFieldDefaults.colors(Color.Transparent)
            )
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(start = 10.dp, top = 40.dp , bottom = 20.dp)
                    .width(300.dp),
                colors = ButtonDefaults.buttonColors( Color(0xff5B67CA)), // تعيين اللون
                shape = RoundedCornerShape(10.dp) //
            ) {
                Text(text = "Create", color = Color.White)
            }
            DividerWithTextForSignUp()
            Image(painter = painterResource(id = R.drawable.google_image), contentDescription =""  ,
                modifier = Modifier.padding(start = 10.dp, top = 30.dp ).clickable {  navController.navigate("loginWithGoogle") }
            )
            Text(
                text = "Have any account? Sign In",
                modifier = Modifier.padding(start = 20.dp, top = 80.dp)
                    .clickable { navController.navigate(Screens.Authentication.Login.rout) },
                color = Color.DarkGray
            )

        }

    }
}
@Composable
fun DividerWithTextForSignUp() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(
            text = "or with",
            color = Color.Gray
        )
        Divider(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp ),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}