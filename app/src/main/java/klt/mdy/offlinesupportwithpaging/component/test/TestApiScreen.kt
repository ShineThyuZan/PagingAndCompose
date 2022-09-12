package klt.mdy.offlinesupportwithpaging.component.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.graph.AppDestination
import klt.mdy.offlinesupportwithpaging.internet.ConnectivityState
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel
import klt.mdy.offlinesupportwithpaging.ui.meme_udf.MeMeEvent
import klt.mdy.offlinesupportwithpaging.ui.meme_udf.MemeAction


@Composable
fun MemeScreen(
    vm: MainViewModel,
    navController: NavController
) {
    val connection by ConnectivityState()
    MemeView(
        navController = navController,
        vm = vm
    )
}


@Composable
fun MemeView(
    vm: MainViewModel,
    navController: NavController,
) {
    LaunchedEffect(key1 = true) {
        vm.getMeMEApi()
        vm.getLanguages()

        vm.memeEvent.collect {
            when (it) {
                MeMeEvent.NavigateToUserProfile -> {
                    navController.navigate(AppDestination.UserInfo.route)
                }
            }
        }
    }
    MemeContent(
        titleLarge = stringResource(id = R.string.app_name),
        onItemClicked = {
            vm.onMeMeAction(MemeAction.ClickMeMeItem)
        },
        resourceCountries = vm.testApiData.value.languages,
        onRetry = {}
    )
}
