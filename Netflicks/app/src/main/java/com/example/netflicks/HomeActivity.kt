package com.example.netflicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.netflicks.ui.theme.NetflicksTheme

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflicksTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen {
                            navController.navigate("tutorial")
                        }
                    }
                    composable("tutorial") {
                        TutorialScreen {
                            navController.navigate("home")
                        }
                    }
                    composable("home") {
                        HomeScreen { movieId ->
                            navController.navigate("details/$movieId")
                        }
                    }
                    composable(
                        "details/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        DetailsScreen(backStackEntry.arguments?.getInt("movieId")!!) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}