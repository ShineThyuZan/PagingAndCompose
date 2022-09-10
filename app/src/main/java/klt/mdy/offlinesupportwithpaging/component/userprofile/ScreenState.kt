package klt.mdy.offlinesupportwithpaging.component.userprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.theme.dimen


@Composable
fun ScreenState(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    title: String = "",
    message: String = "",
    onActionClicked: () -> Unit? = {},
    actionLabel: String = "",
    actionColor: Color = MaterialTheme.colorScheme.primary,
    isListState : Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimen.base_2x),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        painter?.let {
            Image(painter = it, contentDescription = "No Internet Connection")

        }

        if (title.isNotEmpty()) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
        message.let {
            if (message.isNotEmpty()) {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        }
        if (!isListState) {
            if (actionLabel.isNotEmpty()) {
                TextButton(onClick = { onActionClicked() }) {
                    Text(text = actionLabel, color = actionColor)
                }
            }
        }else{
            if (actionLabel.isNotEmpty()) {
                TextButton(onClick = { onActionClicked() }) {
                    Text(text = actionLabel, color = actionColor)
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorItemPreview() {
    ScreenState(
        modifier = Modifier,
        title = "No Internet Connection",
        message = "Please check your internet connection"
    )
}

@Composable
@Preview(showBackground = true)
private fun ErrorItemWithButtonPreview() {
    ScreenState(
        modifier = Modifier,
        title = "No Internet Connection",
        message = "Please check your internet connection",
        actionLabel = "Retry",
        onActionClicked = {}
    )
}