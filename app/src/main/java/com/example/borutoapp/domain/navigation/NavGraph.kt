package com.example.borutoapp.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.borutoapp.presentation.screens.home.HomeScreen
import com.example.borutoapp.presentation.screens.search.SearchScreen
import com.example.borutoapp.presentation.screens.splash.SplashScreen
import com.example.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.example.borutoapp.utils.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            SplashScreen(
                navHostController = navController
            )
        }
        composable(
            route = Screen.Welcome.route
        ) {
            WelcomeScreen(navHostController = navController)
        }
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument(DETAILS_ARGUMENT_KEY) {
                    type = NavType.IntType
                }
            )
        ) {

        }
        composable(
            route = Screen.Search.route
        ) {
            SearchScreen(
                navHostController = navController
            )
        }
    }
}