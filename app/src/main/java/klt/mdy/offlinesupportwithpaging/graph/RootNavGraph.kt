package klt.mdy.offlinesupportwithpaging.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import klt.mdy.offlinesupportwithpaging.component.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Splash.route,
        route = Routes.ROOT_ROUTE
    ) {
        composable(route = AppDestination.Splash.route) {
            SplashScreen(navController = navController)
        }
        movieNavGraph(navController = navController)
    }
}
