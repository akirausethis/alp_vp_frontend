package com.mdanielelel.danielfrontend.navigation

import EventPage
import HomePage
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mdanielelel.danielfrontend.ui.pages.event.HistoryPage
import com.mdanielelel.danielfrontend.ui.pages.event.IOCRPage
import com.mdanielelel.danielfrontend.ui.pages.event.ProfilePage
import com.mdanielelel.danielfrontend.ui.pages.login.LoginPage
import com.mdanielelel.danielfrontend.ui.pages.register.RegisterPage

@Composable
fun NavigationGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Pages.Login.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Pages.Login.route) {
            LoginPage(navController = navController)
        }
        composable(Pages.Register.route) {
            RegisterPage(navController = navController)
        }
        composable(Pages.Home.route) {
            HomePage(navController = navController, modifier = Modifier)
        }
        composable(Pages.Profile.route) {
            ProfilePage(navController = navController)
        }
        composable(Pages.Event.route) {
            EventPage(navController = navController)
        }
        composable(Pages.IoCr.route) {
            IOCRPage(navController = navController)
        }
        composable(Pages.History.route) {
            HistoryPage(navController = navController)
        }
    }
}