package com.mdanielelel.danielfrontend.navigation

sealed class Pages(val route: String) {
    data object Register : Pages("register")
    data object Login : Pages("login")
    data object Home : Pages("home")
    data object Profile : Pages("profile")
    data object Event : Pages("event")
    data object IoCr : Pages("io-cr")
    data object History : Pages("history")
}