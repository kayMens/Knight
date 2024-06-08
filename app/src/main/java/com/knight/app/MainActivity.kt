package com.knight.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.knight.app.ui.screens.LoadingScreen
import com.knight.app.ui.screens.MainScreen
import com.knight.app.ui.theme.KnightTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KnightTheme {
                val navController = rememberNavController()
                val enterTransition = fadeIn()
                val exitTransition = fadeOut()

                NavHost(
                    navController = navController,
                    startDestination = Route.MAIN.destination
                ) {
                    composable(
                        route = Route.MAIN.destination,
                        enterTransition = { enterTransition },
                        exitTransition = { exitTransition }
                    ) {
                        MainScreen { route ->
                            navController.navigate(route)
                        }
                    }

                    composable(
                        route = Route.LOADING.destination,
                        enterTransition = { enterTransition },
                        exitTransition = { exitTransition }
                    ) {
                        LoadingScreen()
                    }
                }
            }
        }
    }
}

enum class Route(val destination: String) {
    MAIN("main"), LOADING("loading")
}