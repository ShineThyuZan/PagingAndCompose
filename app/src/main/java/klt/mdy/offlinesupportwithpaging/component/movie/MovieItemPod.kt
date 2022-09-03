package klt.mdy.offlinesupportwithpaging.component.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import klt.mdy.offlinesupportwithpaging.common.Endpoints
import klt.mdy.offlinesupportwithpaging.model.MovieEntity


@Composable
fun MovieItemPod(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onItemClicked: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    8.dp
                )
            )
            .clickable {
                onItemClicked(movie.movieId)
            }
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            text = movie.originalTitle,
            style = MaterialTheme.typography.h6
        )
        movie.coverUrl?.let { imageLink ->
            val imageUrl = rememberCoilPainter(
                request = Endpoints.IMAGE_URL + imageLink,
                fadeIn = true
            )
            Image(
                painter = imageUrl,
                contentDescription = "Avatar Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size = 68.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Surface {
        MovieItemPod(
            modifier = Modifier,
            movie = MovieEntity(
                coverUrl = "",
                posterUrl = "",
                overview = "This is the overview of the selected movie item. " +
                        "How's about the content preview. This gonna be GG. " +
                        "This overview will contain the description of a movie.",
                originalTitle = "Original Title",
                movieTitle = "Title",
                popularity = 1.2,
                releasedDate = "12.12.2022",
                totalVote = 1.2,
                averageVote = 1.2,
                language = "en"
            ),
            onItemClicked = {}
        )
    }
}