package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.theme.dimen

@Composable
fun AvatarUrl(
    url: String,
    size: Dp,
    onClickedAvatarImage: () -> Unit,
    isGroup : Boolean = false
) {


    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier = Modifier
                .size(size = size)
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
                .clickable {
                    onClickedAvatarImage()
                },
            contentAlignment = Alignment.Center
        ) {
            val image = rememberCoilPainter(
                request = url,
                fadeIn = true
            )
            Image(
                painter = image,
                contentDescription = "Avatar Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size = size)
                    .clip(CircleShape)
                    .clickable { onClickedAvatarImage() }
            )
        }

        if (isGroup){
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 1.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                        contentDescription = "group",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

            }
        }

    }

}

@Composable
fun AvatarText(
    name: Char,
    size: Dp,
    textSize: TextStyle,
    onClickedAvatarImage: () -> Unit,
    isGroup : Boolean = false
) {

    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier = Modifier
                .size(size = size)
                .clip(CircleShape)
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
                .clickable {
                    onClickedAvatarImage()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name.toString().uppercase(),
                style = textSize,
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (isGroup){
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.surface)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 1.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_downward_24),
                        contentDescription = "group",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

            }
        }

    }
}

@Composable
@Preview
private fun AvatarTextPreview() {
    Surface {
        AvatarText(
            name = 'K',
            size = MaterialTheme.dimen.base_6x,
            textSize = MaterialTheme.typography.titleMedium,
            onClickedAvatarImage = {}
        )
    }
}
@Composable
@Preview
private fun GroupAvatarTextPreview() {
    Surface {
        AvatarText(
            name = 'K',
            size = MaterialTheme.dimen.base_6x,
            textSize = MaterialTheme.typography.titleMedium,
            onClickedAvatarImage = {},
            isGroup = true
        )
    }
}