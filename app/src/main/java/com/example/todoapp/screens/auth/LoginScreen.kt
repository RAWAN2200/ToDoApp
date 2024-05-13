package com.example.todoapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.component.LoginWithGoogle
import com.example.todoapp.ui.theme.PrimaryColor


@Composable
fun LoginScreen(navController: NavController,
                authViewModel: AuthViewModel )
{

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Text(
        modifier = Modifier.padding(vertical = 50.dp, horizontal = 16.dp),
        text = "Login",
        color = PrimaryColor,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            leadingIcon = {
                Icon(Icons.Outlined.Email, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),


                )
        )
        TextField(
            leadingIcon = {
                Icon(Icons.Outlined.Lock, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth(0.9f),
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.White.copy(0.5f),
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    authViewModel.resetPassword(email.value.text)
                },
            text = "Forgot Password?",
            color = PrimaryColor,
            fontSize = 12.sp,
            textAlign = TextAlign.End
        )

        Button(
            onClick = {
                authViewModel.login(email.value.text, password.value.text)

            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp), text = "Login",
                fontSize = 16.sp,
                color = Color.White
            )

        }
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Divider(color = Color.Gray.copy(0.2f))
            Text(
                modifier = Modifier
                    .background((Color.White))
                    .padding(vertical = 8.dp), text = "Or With",
                fontSize = 16.sp,
                color = Color.Gray.copy(0.8f)
            )
        }

        LoginWithGoogle(authViewModel= authViewModel)
    }
}





































//
//    var email by remember { mutableStateOf(TextFieldValue("")) }
//    var pass by remember { mutableStateOf(TextFieldValue("")) }
//    Column {
//        Text(
//            text = "Login",
//            fontSize = 32.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xff5B67CA),
//            modifier = Modifier.padding(start = 30.dp, top = 100.dp)
//        )
//
//        Column(modifier = Modifier
//            .fillMaxSize() ,
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TextField(
//                value = email,
//                onValueChange = { email = it },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email,
//                        contentDescription = "emailIcon"
//                    )
//                },
//                label = { Text(text = "Email ID or Username") },
//                placeholder = { Text(text = "") },
//                colors = TextFieldDefaults.colors(Color.Transparent),
//
//
//                )
//            TextField(
//                value = pass,
//                onValueChange = { pass = it },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = "emailIcon",
//
//                        )
//                },
//                label = { Text(text = "Password") },
//                placeholder = { Text(text = "") },
//                colors = TextFieldDefaults.colors(Color.Transparent)
//            )
//            Text(text = "Forgot Password ?",
//                color = Color(0xff5B67CA),
//                modifier = Modifier.padding(start = 200.dp, top = 50.dp).clickable {
//                    authViewModel.resetPassword(emailState.value.text)
//                },
//            )
//            Button(
//                onClick = {},
//                modifier = Modifier
//                    .padding(start = 10.dp, top = 40.dp , bottom = 20.dp)
//                    .width(300.dp),
//                colors = ButtonDefaults.buttonColors( Color(0xff5B67CA)), // تعيين اللون
//                shape = RoundedCornerShape(10.dp) //
//            ) {
//                Text(text = "Login", color = Color.White)
//            }
//            DividerWithText()
//            Image(painter = painterResource(id = R.drawable.google_image), contentDescription =""  ,
//                modifier = Modifier.padding(start = 10.dp, top = 30.dp ).clickable {  navController.navigate("loginWithGoogle") }
//                )
//            Text(
//                text = "Don’t have an account? Sign Up",
//                modifier = Modifier.padding(start = 20.dp, top = 80.dp).clickable {  navController.navigate("signUp") } ,
//                color = Color.DarkGray
//            )
//
//        }
//
//    }
//}
//@Composable
//fun DividerWithText() {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Divider(
//            modifier = Modifier
//                .weight(1f)
//                .padding(end = 8.dp),
//            color = Color.Gray,
//            thickness = 1.dp
//        )
//        Text(
//            text = "or with",
//            color = Color.Gray
//        )
//        Divider(
//            modifier = Modifier
//                .weight(1f)
//                .padding(start = 8.dp ),
//            color = Color.Gray,
//            thickness = 1.dp
//        )
//    }
//}
//

