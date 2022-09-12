package klt.mdy.offlinesupportwithpaging.component.userprofile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.component.CommonTopBar
import klt.mdy.offlinesupportwithpaging.component.TopBarActionType
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel


@Composable
fun UserInfoScreen(
    navController: NavController,
    vm: MainViewModel
) {
    AccountView(
        navController = navController,
        vm = vm
    )
}

@Composable
fun AccountView(
    navController: NavController,
    vm: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val userInfo = vm.userState.value.form.profileInfo.username

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                navIcon = painterResource(id = R.drawable.ic_baseline_close_24),
                onNavIconClicked = {
                    navController.popBackStack()
                },
                title = userInfo,
                topBarActionType = TopBarActionType.ACTION_LABEL,
                actionLabel = "Sheet",
                onActionLabelClicked = {
                },
            )
        },
        content = {
            LanguageScreen(vm = vm)
        }
    )
}

@Composable
fun AccountContent(
    modifier: Modifier,
    userName: String
) {
    Surface(color = Color.Transparent) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = userName)
        }
    }
}

@Composable
@Preview
private fun AccountPreview() {
    Surface {
        AccountContent(
            modifier = Modifier,
            userName = "Ko Ko Tun In your Area"
        )
    }
}