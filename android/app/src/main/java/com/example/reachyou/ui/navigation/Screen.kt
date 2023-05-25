package com.example.reachyou.ui.navigation

sealed class Screen(val route: String){
    object Landing: Screen("landing")
}
