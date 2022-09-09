package klt.mdy.offlinesupportwithpaging.component.test

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.offlinesupportwithpaging.common.Resource
import klt.mdy.offlinesupportwithpaging.component.CommonListItem
import klt.mdy.offlinesupportwithpaging.model.test.DataObjVos
import klt.mdy.offlinesupportwithpaging.theme.dimen

@Composable
fun MemeContent(
    modifier: Modifier = Modifier,
    titleLarge: String,
    resourceCountries: Resource<List<DataObjVos>>,
    onItemClicked: () -> Unit,
    onRetry: () -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            when (resourceCountries) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Text(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.dimen.base_4x),
                        text = titleLarge,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center
                    )
                    MemeListView(
                        languages = resourceCountries.data ?: listOf(),
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}


@Composable
fun MemeListView(
    modifier: Modifier = Modifier,
    languages: List<DataObjVos>,
    onItemClicked: () -> Unit
) {
    LazyColumn(
        content = {
            items(
                count = languages.size
            ) { index ->
                val currentItem = languages[index]
                MemeItem(
                    title = currentItem.name,
                    description = currentItem.name,
                    onItemClicked = { onItemClicked() }
                )
            }
        }
    )
}

@Composable
fun MemeItem(
    modifier: Modifier = Modifier,
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
@Preview(showBackground = true)
private fun Preview() {
    Surface {
        MemeItem(
            title = "Me Me Title",
            description = "Me Me description",
            onItemClicked = {}
        )
    }
}
