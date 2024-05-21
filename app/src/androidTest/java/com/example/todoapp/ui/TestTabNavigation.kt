package com.example.todoapp.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.todoapp.MainActivity
import com.example.todoapp.navigation.Screens
import com.example.todoapp.screens.auth.AuthViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test


import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.todoapp.component.BottomBar
import com.example.todoapp.navigation.EventsAppNavigation

import com.example.todoapp.ui.theme.ToDoAppTheme

import dagger.hilt.android.testing.HiltAndroidTest




@HiltAndroidTest
class TestTabNavigation {


    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var authViewModel: AuthViewModel
    private lateinit var navController: TestNavHostController

    @Before
    fun init(){
        hiltRule.inject()
        composeTestRule.activity.setContent {
            authViewModel = hiltViewModel()
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(DialogNavigator())
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            ToDoAppTheme {
                EventsAppNavigation(authViewModel, navController)
                BottomBar(navController)

            }

        }

    }



    @Test
    fun testBottomBarNavigation(){

        composeTestRule.onNodeWithTag("Navigate To Add Screen").assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.AddScreen.rout)


        composeTestRule.onNodeWithTag(Screens.MainApp.TaskByDate.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.TaskByDate.rout)


        composeTestRule.onNodeWithTag(Screens.MainApp.Home.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.Home.rout)


        composeTestRule.onNodeWithTag(Screens.MainApp.CategoryScreen.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.CategoryScreen.rout)


        composeTestRule.onNodeWithTag(Screens.MainApp.StaticScreen.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.StaticScreen.rout)

        composeTestRule.onNodeWithTag(Screens.MainApp.Home.rout).assertIsDisplayed().performClick()
        navController.assertCurrentRouteName(Screens.MainApp.Home.rout)

    }
}

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    TestCase.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
