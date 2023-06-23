package com.descritas.mvvm_jetpack_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.descritas.mvvm_jetpack_project.ui.theme.MVVM_Jetpack_projectTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_Jetpack_projectTheme{
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            val vm: HomeScreenViewModel by viewModels()
                            HomeScreen(navController, vm)
                        }
                        composable("users/{userId}") { backStackEntry ->
                            val vm: UserDetailScreenViewModel by viewModels()
                            UserDetailScreen(navController, vm, (backStackEntry.arguments?.getString("userId", "") ?: "").toLong())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MVVM_Jetpack_projectTheme {
        Greeting("Android")
    }
}