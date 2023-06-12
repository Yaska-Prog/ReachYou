package com.example.reachyou.ui.navigation

sealed class Screen(val route: String){
    object Landing: Screen("landing")
    object Login: Screen("login")
    object Register: Screen("register")
    object Home: Screen("home")
    object News: Screen("news")
    object DetailNews: Screen("news/{id}"){
        fun createRoute(id: Int) = "news/$id"
    }
    object Quiz: Screen("quiz")
    object Profile: Screen("profile")
    object CreateNews: Screen("createNews")
    object SetupProfile: Screen("setupProfile")
    object ScannerBISINDO: Screen("scannerBisindo/{index}"){
        fun createRoute(index: Int) = "scannerBisindo/$index"
    }
    object LaporBug: Screen("laporBug")
    object DetailQuiz: Screen("detailQuiz/{id}"){
        fun createRoute(id: Int) = "detailQuiz/$id"
    }
}
