package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.R
import klt.mdy.offlinesupportwithpaging.component.userprofile.SearchBar
import klt.mdy.offlinesupportwithpaging.theme.dimen


enum class TopBarActionType {
    ACTION_LABEL,
    NO_ACTION_ICON,
    ONE_ACTION_ICON,
    TWO_ACTION_ICONS,
    THREE_ACTION_ICONS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,

    bgColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
    contentColor: Color = MaterialTheme.colorScheme.onSurface,

    navIcon: Painter? = null,
    onNavIconClicked: () -> Unit = {},

    isEnableActionLabel: Boolean? = true,
    title: String = "",
    actionLabel: String = "",

    onActionLabelClicked: () -> Unit = {},
    firstActionIcon: Painter? = null,
    onClickFirstActionIcon: () -> Unit = {},
    secondActionIcon: Painter? = null,
    onClickSecondActionIcon: () -> Unit = {},
    thirdActionIcon: Painter? = null,
    onClickThirdActionIcon: () -> Unit = {},

    topBarActionType: TopBarActionType? = null,

    searchWidgetState: SearchWidgetState = SearchWidgetState.CLOSED,
    searchQuery: String = "",
    onTextChanged: (String) -> Unit = {},
    onCancelSearchClicked: () -> Unit = {},
    onSearchCommitted: (String) -> Unit = {},
    onClearIconClicked: () -> Unit = {},

    ) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            SmallTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = bgColor,
                    navigationIconContentColor = contentColor,
                    titleContentColor = contentColor,
                    actionIconContentColor = contentColor
                ),
                navigationIcon = {
                    navIcon?.let {
                        IconButton(
                            onClick = onNavIconClicked
                        ) {
                            Icon(
                                painter = navIcon,
                                contentDescription = "Navigation Icon",
                            )
                        }
                    }
                },
                title = { Text(text = title) },
                actions = {
                    topBarActionType?.let {
                        when (topBarActionType) {
                            TopBarActionType.NO_ACTION_ICON -> {

                            }
                            TopBarActionType.ACTION_LABEL -> {
                                TextButton(
                                    modifier = modifier.padding(end = MaterialTheme.dimen.small),
                                    enabled = isEnableActionLabel!!,
                                    onClick = { onActionLabelClicked() },
                                ) {
                                    Text(text = actionLabel)
                                }
                            }
                            TopBarActionType.ONE_ACTION_ICON -> {
                                IconButton(
                                    onClick = onClickFirstActionIcon
                                ) {
                                    Icon(
                                        painter = firstActionIcon!!,
                                        contentDescription = "First Action Icon",
                                    )
                                }
                            }
                            TopBarActionType.TWO_ACTION_ICONS -> {
                                IconButton(
                                    onClick = onClickFirstActionIcon
                                ) {
                                    Icon(
                                        painter = firstActionIcon!!,
                                        contentDescription = "First Action Icon",
                                    )
                                }
                                IconButton(
                                    onClick = onClickSecondActionIcon
                                ) {
                                    Icon(
                                        painter = secondActionIcon!!,
                                        contentDescription = "Second Action Icon",
                                    )
                                }
                            }
                            TopBarActionType.THREE_ACTION_ICONS -> {
                                IconButton(
                                    onClick = onClickFirstActionIcon
                                ) {
                                    Icon(
                                        painter = firstActionIcon!!,
                                        contentDescription = "First Action Icon",
                                    )
                                }
                                IconButton(
                                    onClick = onClickSecondActionIcon
                                ) {
                                    Icon(
                                        painter = secondActionIcon!!,
                                        contentDescription = "Second Action Icon",
                                    )
                                }
                                IconButton(
                                    onClick = onClickThirdActionIcon
                                ) {
                                    Icon(
                                        painter = thirdActionIcon!!,
                                        contentDescription = "Third Action Icon",
                                    )
                                }
                            }
                        }
                    }
                },
            )
        }
        SearchWidgetState.OPENED -> {
            SearchBar(
                searchQuery = searchQuery,
                navIcon = painterResource(id = R.drawable.ic_baseline_close_24),
                onNavIconClicked = onCancelSearchClicked,
                onTextChanged = onTextChanged,
                onSearchClicked = onSearchCommitted,
                onClearQueryClicked = onClearIconClicked,
                searchPlaceholder = "Search"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    CommonTopBar(
//      bgColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
        bgColor = Color.Transparent,
        title = "Small Title",
        firstActionIcon = painterResource(id = R.drawable.ic_baseline_search_24),
        secondActionIcon = painterResource(id = R.drawable.ic_baseline_search_24),
        thirdActionIcon = painterResource(id = R.drawable.ic_baseline_search_24),
        topBarActionType = TopBarActionType.ACTION_LABEL,
        onClickFirstActionIcon = {},
        onClickSecondActionIcon = {},
        onClickThirdActionIcon = {},
    )
}