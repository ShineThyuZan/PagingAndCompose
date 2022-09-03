package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.graph.Routes
import klt.mdy.offlinesupportwithpaging.theme.dimen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController,
) {
    var logoAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (logoAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        logoAnimation = true
        delay(100)
        navController.popBackStack()
        navController.navigate(Routes.MOVIE_ROUTE)
    }
    BrandingView(alpha = alphaAnimation.value)
}

@Composable
fun BrandingView(
    alpha: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = modifier
                        .size(MaterialTheme.dimen.large)
                        .alpha(alpha = alpha),
                    imageVector = ImageVector.vectorResource(
                        id = R.drawable.ic_logo
                    ),
                    contentDescription = "Logo Icon",
                )
            }
            Box(
                modifier = modifier.weight(0.2f),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier.alpha(alpha = alpha)
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    BrandingView(alpha = 1f)
}
