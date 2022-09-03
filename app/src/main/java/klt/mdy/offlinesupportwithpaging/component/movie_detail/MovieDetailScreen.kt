package klt.mdy.offlinesupportwithpaging.component.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.model.MovieParcel
import klt.mdy.offlinesupportwithpaging.ui.MainViewModel


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

@Composable
fun MovieDetailContent(
    navController: NavController,
    movieParcel: MovieParcel?,
    vm: MainViewModel
) {
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
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
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
        }

        if (movieParcel!!.posterUrl.isNullOrEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Post Image",
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        } else {
            //FeedImage(imageUrl = movieParcel.posterUrl!!)
            movieParcel.posterUrl?.let {
                AsyncImage(
                    model = Endpoints.IMAGE_URL + it,
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth(),
                    placeholder = painterResource(id = R.drawable.ic_logo),
                )
            }
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
