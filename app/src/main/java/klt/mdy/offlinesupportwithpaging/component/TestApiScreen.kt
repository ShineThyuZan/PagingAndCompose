package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel


@Composable
fun LanguageScreen(
    vm: MainViewModel,
    navController: NavController
) {
    LanguageView(
        navController = navController,
        vm = vm
    )
}

@Composable
fun LanguageView(
    vm: MainViewModel,
    navController: NavController
) {
    LanguageContent(
        titleLarge = stringResource(id = R.string.app_name),
        onItemClicked = {},
        resourceCountries = vm.testApiData.value.languages,
        onRetry = {}
    )
}
