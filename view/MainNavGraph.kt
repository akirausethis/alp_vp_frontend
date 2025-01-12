package com.example.alp_vp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.view.HOME.HomePage
import com.example.alp_vp.view.NEWS.Details.NewsScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomePage(navController = navController)
        }
        composable("detailScreen") {
            NewsScreen(navController = navController)
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    MainNavGraph(navController = navController)
}
