package klt.mdy.offlinesupportwithpaging.component.userprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.component.ShimmerView
import klt.mdy.offlinesupportwithpaging.internet.ConnectionState
import klt.mdy.offlinesupportwithpaging.internet.ConnectivityState
import klt.mdy.offlinesupportwithpaging.model.user.LanguageItem
import klt.mdy.offlinesupportwithpaging.model.user.LanguageVo


@Composable
fun LanguageContent(
    modifier: Modifier = Modifier,
    resourceCountries: Resource<List<LanguageVo>>,
    onItemClicked: (LanguageVo) -> Unit,
    onRetry: () -> Unit
) {
    val connectionState by ConnectivityState()

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            when (resourceCountries) {
                is Resource.Error -> {
                    if (connectionState == ConnectionState.UnAvailable) {
                        ScreenState(
                            title = "No Internet Connection",
                            message = "Check Internet Connection",
                            onActionClicked = {
                                onRetry()
                            },
                            actionLabel = "Retry",
                            actionColor = MaterialTheme.colorScheme.primary
                        )
                    } else {
                        /* internet access and cannot load response */
                        ScreenState(
                            title = "Server Error!!",
                            message = "Server is under maintenance !!"
                        )
                    }
                }
                is Resource.Loading -> {
                    ShimmerView()
                }
                is Resource.Success -> {
                    LanguageListView(
                        languages = resourceCountries.data ?: listOf(),
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}


@Composable
fun LanguageListView(
    languages: List<LanguageVo>,
    onItemClicked: (LanguageVo) -> Unit
) {
    LazyColumn(
        content = {
            items(
                count = languages.size
            ) { index ->
                val currentItem = languages[index]
                LanguageItem(
                    title = currentItem.name,
                    description = currentItem.description,
                    onItemClicked = { onItemClicked(currentItem) }
                )
            }
        }
    )
}


@Composable
@Preview
private fun LanguageListPreview() {
    Surface {
        LanguageListView(
            onItemClicked = {},
            languages = listOf(
                LanguageVo(
                    name = "မြန်မာ",
                    description = "Burmese",
                    locale = 0
                ),
                LanguageVo(
                    name = "English",
                    description = "English",
                    locale = 1
                ),
                LanguageVo(
                    name = "简体中文",
                    description = "Chinese, Simplified",
                    locale = 2
                ),
            )
        )
    }
}

