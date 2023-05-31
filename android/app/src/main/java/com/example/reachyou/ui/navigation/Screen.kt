package com.example.reachyou.ui.navigation

sealed class Screen(val route: String){
    object Landing: Screen("landing")
    object Login: Screen("login")
    object Register: Screen("register")
    object SetUpProfile: Screen("setUpProfile")
    object Home: Screen("home")
    object News: Screen("news")
    object Quiz: Screen("quiz")
    object Profile: Screen("profile")
}
