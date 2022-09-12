package klt.mdy.offlinesupportwithpaging.component.userprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.theme.dimen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    bgColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
    navIcon: Painter? = null,
    onNavIconClicked: () -> Unit = {},
    searchQuery: String = "",
    searchPlaceholder: String = "",
    onTextChanged: (String) -> Unit = {},
    onClearQueryClicked: () -> Unit = {},
    onSearchClicked: (String) -> Unit = {},
) {
    SmallTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = bgColor,
        ),
        navigationIcon = {
            navIcon?.let {
                IconButton(
                    onClick = {
                        onNavIconClicked()
                    }
                ) {
                    Icon(
                        painter = navIcon,
                        contentDescription = "Navigation Icon",
                    )
                }
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimen.base_6x)
                    .padding(end = MaterialTheme.dimen.base_2x)
                    .clip(CircleShape)
                    .background(
                        color = MaterialTheme.colorScheme.surface
                    )
                    .padding(start = MaterialTheme.dimen.base_2x),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "Search"
                )

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false),
                    onValueChange = onTextChanged,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    value = searchQuery,
                    decorationBox = {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = searchPlaceholder,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        it()
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchClicked(searchQuery)
                        }
                    ),
                    cursorBrush = SolidColor(
                        value = MaterialTheme.colorScheme.primary,
                    ),
                )

                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = onClearQueryClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_close_24),
                            contentDescription = "Clear Value"
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview
private fun SearchBarPreview() {
    SearchBar(
        searchQuery = "Sar Tharr",
        searchPlaceholder = "Search",
        onTextChanged = {},
        onNavIconClicked = {},
        onClearQueryClicked = {},
        onSearchClicked = {}
    )
}
