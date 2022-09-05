package klt.mdy.offlinesupportwithpaging.component.movie_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.component.SheetHeader


@Composable
fun DownloadSheetView(
    onItemClick: (moreActionStatus: Int) -> Unit) {
    Column {
        SheetHeader()
        SheetItem(onItemClick = onItemClick)
    }
}

@Composable
@Preview
private fun SheetPreview() {
    Surface {
        DownloadSheetView(
            onItemClick = {}
        )
    }
}
