package klt.mdy.offlinesupportwithpaging.component.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import klt.mdy.offlinesupportwithpaging.common.Endpoints

@Composable
fun FeedImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    val postImage = rememberCoilPainter(
        request = Endpoints.IMAGE_URL + imageUrl,
        fadeIn = true
    )
    Image(
        painter = postImage,
        contentDescription = "Post Image",
        modifier = modifier
            .height(400.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )

}



@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    val postImage = rememberCoilPainter(
        request = Endpoints.IMAGE_URL + imageUrl,
        fadeIn = true
    )
    Image(
        painter = postImage,
        contentDescription = "ProfileImage",
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )

}