package klt.mdy.offlinesupportwithpaging.model.user

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.component.CommonListItem

@Composable
fun LanguageItem(
    title: String,
    description: String,
    onItemClicked: () -> Unit,
) {
    CommonListItem(
        modifier = Modifier,
        headlineText = title,
        supportingText = description,
        onClickListItem = onItemClicked
    )
}

@Composable
@Preview
private fun LanguageItemPreview() {
    Surface {
        LanguageItem(
            title = "English",
            description = "English",
            onItemClicked = {}
        )
    }
}