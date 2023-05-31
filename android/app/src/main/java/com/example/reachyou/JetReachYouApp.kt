package com.example.reachyou

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reachyou.model.BottomBarItem
import com.example.reachyou.ui.navigation.Screen
import com.example.reachyou.ui.screen.home.HomeScreen
import com.example.reachyou.ui.screen.landing.LandingScreen
import com.example.reachyou.ui.screen.login.LoginScreen
import com.example.reachyou.ui.screen.register.RegisterScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetReachYouApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar ={
            if(currentRoute != Screen.Landing.route ||
                currentRoute != Screen.Login.route ||
                currentRoute != Screen.Register.route ||
                currentRoute != Screen.SetUpProfile.route){
                BottomBar(navController = navController)
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)){
            composable(Screen.Landing.route){
                LandingScreen(modifier = Modifier,
                    navigateToLogin = {navController.navigate(Screen.Login.route)},
                    navigateToRegister = {navController.navigate(Screen.Register.route)})
            }
            composable(Screen.Login.route){
                LoginScreen()
            }
            composable(Screen.Register.route){
                RegisterScreen()
            }
            composable(Screen.Home.route){
                HomeScreen()
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    var selectedItem by remember {
        mutableStateOf(0)
    }
    val bottomNavItem = listOf<BottomBarItem>(
        BottomBarItem("home", Icons.Default.Home, Screen.Home),
        BottomBarItem("news", ImageVector.vectorResource(id = R.drawable.newspaper_icon), Screen.News),
        BottomBarItem("quiz", ImageVector.vectorResource(id = R.drawable.question_answer), Screen.Quiz),
        BottomBarItem("profile", ImageVector.vectorResource(id = R.drawable.person), Screen.Profile),
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        containerColor = Color(android.graphics.Color.parseColor("#064958")),
        contentColor = Color.Yellow
    ) {
        bottomNavItem.forEachIndexed { index, item ->
            NavigationBarItem(selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                  },
                icon = {
                    Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                        tint = Color(android.graphics.Color.parseColor("#FFDB58"))
                    )
                },
                label = { Text(text = item.title, color = Color.White)},
            )
        }
    }
}