package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.theme.dimen

@Composable
fun SheetHeader(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.base))
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .width(MaterialTheme.dimen.extra_large)
                    .height(MaterialTheme.dimen.small)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.onBackground)
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.dimen.base))
    }
}

@Composable
@Preview(showBackground = true)
fun sheetHeaderPreview() {
    SheetHeader()
}
