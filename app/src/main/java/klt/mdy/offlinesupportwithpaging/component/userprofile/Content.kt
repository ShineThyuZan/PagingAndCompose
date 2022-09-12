package klt.mdy.offlinesupportwithpaging.component.userprofile

import androidx.compose.runtime.Composable
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel


@Composable
fun LanguageScreen(
    vm: MainViewModel
) {
    LanguageView(
        vm = vm
    )
}

@Composable
fun LanguageView(vm: MainViewModel) {
    LanguageContent(
        onItemClicked = {},
        resourceCountries = vm.userState.value.languages,
        onRetry = {}
    )
}
