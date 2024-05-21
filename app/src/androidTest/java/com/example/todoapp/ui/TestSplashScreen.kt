package com.example.todoapp.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag

import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController

import com.example.todoapp.screens.auth.SplashScreen
import com.example.todoapp.ui.theme.ToDoAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TestSplashScreen {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()


    private lateinit var navController: TestNavHostController

    @Before
    fun init(){
        hiltRule.inject()

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            SplashScreen(navController = navController)
            }
        }

    @Test
    fun testSplashScreen(){
        composeTestRule.onNodeWithTag("Splash Screen").assertIsDisplayed()
    }
    @Test
    fun testSplashScreenContent(){
        composeTestRule.onNodeWithTag("intro image ").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("title text").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Description Text").assertIsDisplayed()
    }

    @Test
    fun testSplashScreenClickButton(){
        composeTestRule.onNodeWithTag("Login Button").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Login Button").assertHasClickAction()

    }
    }
