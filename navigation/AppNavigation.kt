package com.mdanielelel.danielfrontend.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mdanielelel.danielfrontend.R


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavItems = listOf(
        BottomNavItem(route = Pages.Home.route, iconResId = R.raw.ic_home, label = "Home"),
        BottomNavItem(route = Pages.Event.route, iconResId = R.raw.ic_speaker_phone, label = "Event"),
        BottomNavItem(route = Pages.IoCr.route, iconResId = R.raw.ic_book_fill_open, label = "IO/CR"),
        BottomNavItem(route = Pages.History.route, iconResId = R.raw.ic_clipboard_fill, label = "History"),
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(Pages.Home.route, Pages.Event.route, Pages.IoCr.route, Pages.History.route)) {
                BottomNavigationBar(navController = navController, items = bottomNavItems)
            }
        }
    ) { innerPadding ->
        NavigationGraph(navController = navController, innerPadding = innerPadding)
    }
}