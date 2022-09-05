package klt.mdy.offlinesupportwithpaging.component.movie_detail

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.model.MovieParcel
import klt.mdy.offlinesupportwithpaging.theme.dimen
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieAction
import klt.mdy.offlinesupportwithpaging.ui.udf.MovieEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun MovieDetailScreen(
    navController: NavController,
    movieParcel: MovieParcel?,
    vm: MainViewModel
) {

    MovieDetailContent(
        navController = navController,
        movieParcel = movieParcel,
        vm = vm
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailContent(
    navController: NavController,
    movieParcel: MovieParcel?,
    vm: MainViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    /** Back Action to close bottom sheet */
    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch {
            modalBottomSheetState.hide()
        }
    }
    LaunchedEffect(key1 = true) {
        vm.movieEvent.collectLatest {
            when (it) {
                MovieEvent.ShowDownloadSheet -> {
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                }
                else -> {}
            }
        }
    }

    fun download() {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(Endpoints.IMAGE_URL+ movieParcel!!.posterUrl)
        val request = DownloadManager.Request(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val reference = downloadManager.enqueue(request)
    }

    ModalBottomSheetLayout(
        sheetContent = {
            DownloadSheetView(
                onItemClick = {
                    when (it) {
                        ActionStatus.DOWNLOAD.status -> {
                            scope.launch {
                                modalBottomSheetState.hide()
                            }
                            download()
                        }
                    }
                }
            )
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = androidx.compose.material3.MaterialTheme.dimen.base_2x,
            topEnd = androidx.compose.material3.MaterialTheme.dimen.base_2x,
        ),
        sheetBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface,
        scrimColor = Color.Black.copy(0.7f)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                if (movieParcel!!.coverUrl.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "ProfileImage",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    //  ProfileImage(imageUrl = movieParcel.coverUrl!!)
                    movieParcel.coverUrl?.let {
                        AsyncImage(
                            model = Endpoints.IMAGE_URL + it,
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                // Set image size to 40 dp
                                .size(40.dp)
                                .width(48.dp)
                                .height(48.dp)
                                // Clip image to be shaped as a circle
                                .clip(CircleShape)
                                .align(Alignment.CenterVertically)
                                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movieParcel.movieTitle,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                IconButton(
                    onClick ={
                        vm.onMovieAction(action = MovieAction.ClickMore)
                    }

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                        contentDescription = "MoreOptions",
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            //FeedImage(imageUrl = movieParcel.posterUrl!!)
            movieParcel!!.posterUrl?.let {
                AsyncImage(
                    model = Endpoints.IMAGE_URL + it,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth(),
                )
            }

            Text(
                text = movieParcel.overview?.let {
                    movieParcel.overview
                } ?: "Overview",
                style = MaterialTheme.typography.overline,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}
