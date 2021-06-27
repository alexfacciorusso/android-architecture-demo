package com.alexfacciorusso.architecturedemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexfacciorusso.architecturedemo.ui.login.LoginScreen
import com.alexfacciorusso.architecturedemo.login.LoginViewModel
import com.alexfacciorusso.architecturedemo.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MyApplicationTheme {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(hiltViewModel()) {
                            navController.navigate("success")
                        }
                    }
                    composable("success"){
                        Text("Success!")
                    }
                }
            }
        }
    }
}