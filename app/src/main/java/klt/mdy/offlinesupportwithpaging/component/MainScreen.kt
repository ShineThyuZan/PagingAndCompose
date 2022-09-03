package klt.mdy.offlinesupportwithpaging.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import klt.mdy.offlinesupportwithpaging.graph.RootNavGraph


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            RootNavGraph(navController = navController)
        }
    }
}