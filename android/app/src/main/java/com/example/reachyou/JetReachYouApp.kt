package com.example.reachyou

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.reachyou.data.local.SharedPreferenceManager
import com.example.reachyou.model.BottomBarItem
import com.example.reachyou.ui.component.utils.BottomSheetEditProfile
import com.example.reachyou.ui.navigation.Screen
import com.example.reachyou.ui.screen.QuizScreen.QuizScreen
import com.example.reachyou.ui.screen.createNews.CreateNewsScreen
import com.example.reachyou.ui.screen.detailNews.DetailNewsScreen
import com.example.reachyou.ui.screen.detailQuiz.DetailQuizScreen
import com.example.reachyou.ui.screen.home.HomeScreen
import com.example.reachyou.ui.screen.landing.LandingScreen
import com.example.reachyou.ui.screen.laporBug.LaporBugScreen
import com.example.reachyou.ui.screen.login.LoginScreen
import com.example.reachyou.ui.screen.news.NewsScreen
import com.example.reachyou.ui.screen.profile.ProfileScreen
import com.example.reachyou.ui.screen.register.RegisterScreen
import com.example.reachyou.ui.screen.scannerBISINDO.CameraActivityBisindo
import com.example.reachyou.ui.screen.scannerBISINDO.DetectorActivityBisindo
import com.example.reachyou.ui.screen.scannerBISINDO.DetectorMoneyActivity
import com.example.reachyou.ui.screen.scannerBISINDO.ScannerBISINDOScreen
import com.example.reachyou.ui.screen.setupProfile.SetupProfileScreen
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetReachYouApp(
    navController: NavHostController = rememberNavController(),
    outputDirectory: File,
    sharedPreferenceManager: SharedPreferenceManager = SharedPreferenceManager(LocalContext.current)
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var typeEdit by rememberSaveable{
        mutableStateOf("")
    }
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val user = sharedPreferenceManager.getUser()
    val context = LocalContext.current
    val bottomNavItem = listOf<BottomBarItem>(
        BottomBarItem("home", Icons.Default.Home, Screen.Home),
        BottomBarItem("news", ImageVector.vectorResource(id = R.drawable.newspaper_icon), Screen.News),
        BottomBarItem("quiz", ImageVector.vectorResource(id = R.drawable.question_answer), Screen.Quiz),
        BottomBarItem("profile", ImageVector.vectorResource(id = R.drawable.person), Screen.Profile),
    )
    Scaffold(
        topBar = {},
        bottomBar ={
            if(currentRoute.toString() == Screen.Home.route ||
                currentRoute.toString() == Screen.News.route ||
                currentRoute.toString() == Screen.Quiz.route ||
                currentRoute.toString() == Screen.Profile.route &&
                !sheetState.isVisible){
                Log.d("route", "false $currentRoute")
                NavigationBar (
                    containerColor = Color(android.graphics.Color.parseColor("#064958")),
                    contentColor = Color.Yellow,
                    tonalElevation = 5.dp,
                    modifier = Modifier
                        .graphicsLayer {
                            shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                            clip = true
                        },
                ) {
                    bottomNavItem.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
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
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color(android.graphics.Color.parseColor("#4F4626")),
                            ),
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
        }
    ) {innerPadding ->
        if(sheetState.isVisible){
            ModalBottomSheet(onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            }) {
                BottomSheetEditProfile(
                    type = typeEdit,
                    dismissBottomSheet = {
                        scope.launch {
                            sheetState.hide()
                        }
                    }
                )
            }
        }
        NavHost(
            navController = navController,
            startDestination =  if(user?.username != "" && user?.username != null) Screen.Home.route else Screen.Landing.route,
            modifier = Modifier.padding(innerPadding)){
            composable(Screen.Landing.route){
                LandingScreen(modifier = Modifier,
                    navigateToLogin = {navController.navigate(Screen.Login.route)},
                    navigateToRegister = {navController.navigate(Screen.Register.route)},
                    navigateToScanner = {navController.navigate(Screen.ScannerBISINDO.createRoute(0))})
            }
            composable(Screen.Login.route){
                LoginScreen(
                    navigateToRegister = {navController.navigate(Screen.Register.route)},
                    navigateToHome = {navController.navigate(Screen.Home.route)}
                )
            }
            composable(Screen.SetupProfile.route){
                SetupProfileScreen(
                    navigateToLogin = {navController.navigate(Screen.Login.route)}
                )
            }
            composable(Screen.Register.route){
                RegisterScreen(
                    navigateToLogin = {navController.navigate(Screen.Login.route)},
                    navigateToSetupProfile = {navController.navigate(Screen.SetupProfile.route)}
                )
            }
            composable(Screen.Home.route){
                HomeScreen(
                    navigateToScanner = {index ->
                        navController.navigate(Screen.ScannerBISINDO.createRoute(index))},
                    navigateToBisindo = {context.startActivity(Intent(context, DetectorActivityBisindo::class.java))},
                    navigateToMoney = {context.startActivity(Intent(context, DetectorMoneyActivity::class.java))},
                )
            }
            composable(Screen.Profile.route){
                ProfileScreen(
                    openModalBottomSheet = {inputType->
                        typeEdit = inputType
                        scope.launch {
                            sheetState.show()
                        }
                    },
                    navigateToLaporBug = {navController.navigate(Screen.LaporBug.route)},
                    navigateToLanding = {navController.navigate(Screen.Landing.route)}
                )
            }
            composable(Screen.CreateNews.route){
                CreateNewsScreen(navigateToNews = {navController.navigate(Screen.News.route)})
            }
            composable(Screen.News.route){
                NewsScreen(navigateToCreate = {navController.navigate(Screen.CreateNews.route)},
                    navigateToDetail = {index->
                        navController.navigate(Screen.DetailNews.createRoute(index))
                        }
                    )
            }
            composable(route = Screen.ScannerBISINDO.route, arguments = listOf(navArgument("index"){type = NavType.IntType})){
                val index = it.arguments?.getInt("index") ?: 0
                ScannerBISINDOScreen(outputDirectory = outputDirectory, onError = {}, index = index, navigateToHome = {
                    if(user?.username != "" && user?.username != null) navController.navigate(Screen.Home.route) else navController.navigate(Screen.Landing.route)
                    })
            }
            composable(route = Screen.LaporBug.route){
                LaporBugScreen(onBackButtonPressed = {navController.navigate(Screen.Profile.route)})
            }
            composable(route = Screen.Quiz.route){
                QuizScreen(navigateToDetailQuiz = {id ->
                    navController.navigate(Screen.DetailQuiz.createRoute(id))
                })
            }
            composable(route = Screen.DetailQuiz.route, arguments = listOf(navArgument("id"){type = NavType.IntType})){
                val id = it.arguments?.getInt("id") ?: 0
                DetailQuizScreen(type = id, navigateToQuiz = {navController.navigate(Screen.Quiz.route)})
            }
            composable(route = Screen.DetailNews.route, arguments = (listOf(navArgument("id"){type = NavType.IntType}))){
                val id = it.arguments?.getInt("id")?: 0
                DetailNewsScreen(idNews = id, navigateToNews = {navController.navigate(Screen.News.route)})
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val bottomNavItem = listOf<BottomBarItem>(
        BottomBarItem("home", Icons.Default.Home, Screen.Home),
        BottomBarItem("news", ImageVector.vectorResource(id = R.drawable.newspaper_icon), Screen.News),
        BottomBarItem("quiz", ImageVector.vectorResource(id = R.drawable.question_answer), Screen.Quiz),
        BottomBarItem("profile", ImageVector.vectorResource(id = R.drawable.person), Screen.Profile),
    )
    NavigationBar (
        containerColor = Color(android.graphics.Color.parseColor("#064958")),
        contentColor = Color.Yellow,
        tonalElevation = 5.dp,
        modifier = Modifier
            .graphicsLayer {
                shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                clip = true
            },
    ) {
        bottomNavItem.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
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
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(android.graphics.Color.parseColor("#4F4626")),
                ),
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