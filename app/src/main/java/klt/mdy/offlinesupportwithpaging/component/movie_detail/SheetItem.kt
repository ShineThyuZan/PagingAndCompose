package klt.mdy.offlinesupportwithpaging.component.movie_detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.theme.dimen


@Composable
fun SheetItem(onItemClick: (moreActionStatus: Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .padding(bottom = MaterialTheme.dimen.base_3x),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(items = moreActionData) { index, item ->
            when (index) {
                ActionStatus.DOWNLOAD.status -> {
                    ActionItemContent(
                        drawable = item.drawable,
                        text = item.text,
                        onItemClick = {
                            onItemClick(ActionStatus.DOWNLOAD.status)
                        },
                        itemColor = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
    }
}

@Composable
fun ActionItemContent(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    onItemClick: () -> Unit,
    itemColor: Color
) {
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "null",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .height(24.dp)
                .width(24.dp),
            colorFilter = ColorFilter.tint(color = itemColor)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium,
            color = itemColor
        )
    }
}

private val moreActionData = mutableListOf(
    R.drawable.ic_baseline_arrow_downward_24 to R.string.download,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true)
@Composable
fun CountryBottomSheetListItemPreview() {
    SheetItem(onItemClick = {})
}
