package com.example.todoapp.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.example.todoapp.R
import  androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.navigation.Screens

//import com.example.todoapp.screens.tasks.NameForProfile
import com.google.firebase.auth.FirebaseUser

@Composable
fun profileScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//         Header with user name
//        HeaderName(invoke?.displayName.orEmpty())
//
//         Profile picture
        Card(
            modifier = Modifier
                .size(130.dp)
                .padding(start = 70.dp),
            shape = RoundedCornerShape(50),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_avatar_male),
                contentDescription = "profile picture",
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFF7D88E7))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF858FE9)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.profile),
                                        contentDescription = "profile picture",
                                        modifier = Modifier.size(40.dp),
                                        contentScale = ContentScale.Crop
                                    )

                                }

                            }
                            Text(text = "Personal", color = Color(0xFF10275A))
                            Text(
                                text = "18 Task",
                                modifier = Modifier.padding(top = 10.dp),
                                color = Color.White
                            )
                        }
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFF7DC8E7))
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF7FC9E7)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.work),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .padding(10.dp)
                                    )
                                }
                                Text(text = "Work", color = Color(0xFF10275A))
                                Text(
                                    text = "15 Task",
                                    modifier = Modifier.padding(top = 10.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFFFFE4E4))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFE77D7D)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.lock),
                                        contentDescription = "profile picture",
                                        modifier = Modifier.size(35.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Text(text = "Private")
                            Text(
                                text = "3 Task",
                                modifier = Modifier.padding(top = 10.dp),
                            )
                        }
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFFCBF9D8))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF81E89E)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.users),
                                        contentDescription = "profile picture",
                                        modifier = Modifier.size(35.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Text(text = "Meeting")
                            Text(
                                text = "4 Task",
                                modifier = Modifier.padding(top = 10.dp),
                            )
                        }
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFF7D88E7))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF858FE9)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.lock),
                                        contentDescription = "profile picture",
                                        modifier = Modifier.size(35.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Text(text = "Event")
                            Text(
                                text = "12 Task",
                                modifier = Modifier.padding(top = 10.dp),
                            )
                        }
                        Card(
                            modifier = Modifier
                                .size(200.dp)
                                .padding(start = 20.dp, top = 5.dp),
                            colors = CardDefaults.cardColors(Color(0xFFFFEFEB))
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier.size(50.dp),
                                    shape = RoundedCornerShape(20),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFF0A58E)
                                    ),
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 16.dp
                                    )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "profile picture",
                                        modifier = Modifier.size(35.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Text(text = "Create Board")
                            Text(
                                text = "5 Task",
                                modifier = Modifier.padding(top = 10.dp),
                            )
                        }
                    }
                }
            }
        }
    }}
