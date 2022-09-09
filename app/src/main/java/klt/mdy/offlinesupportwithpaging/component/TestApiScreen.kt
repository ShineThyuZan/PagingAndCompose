package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.graph.AppDestination
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieAction
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieEvent


@Composable
fun MemeScreen(
    vm: MainViewModel,
    navController: NavController
) {
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

        vm.movieEvent.collect {
            when (it) {
                MovieEvent.NavigateToUserProfile -> {
                    navController.navigate(AppDestination.UserInfo.route)
                }
            }
        }
    }

    MemeContent(
        titleLarge = stringResource(id = R.string.app_name),
        onItemClicked = {
            vm.onMovieAction(MovieAction.ClickMeMeItem)
        },
        resourceCountries = vm.testApiData.value.languages,
        onRetry = {}
    )
}
