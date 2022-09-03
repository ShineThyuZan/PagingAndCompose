package klt.mdy.offlinesupportwithpaging.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import klt.mdy.offlinesupportwithpaging.component.MainScreen
import klt.mdy.offlinesupportwithpaging.theme.OfflineSupportWithPagingTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OfflineSupportWithPagingTheme {
                Surface(content = {
                   val navController = rememberNavController()
                    MainScreen(navController)
                })
            }
        }
    }
}
