package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import klt.mdy.offlinesupportwithpaging.theme.dimen

@Composable
fun HorizontalSpacerTiny() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.tiny))
}

@Composable
fun HorizontalSpacerSmall() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.small))
}

@Composable
fun HorizontalSpacerBase() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.base))
}

@Composable
fun HorizontalSpacerBase2x() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.base_2x))
}

@Composable
fun HorizontalSpacerBase3x() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.base_3x))
}

@Composable
fun HorizontalSpacerBase4x() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.base_4x))
}

@Composable
fun HorizontalSpacerBase8x() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.base_8x))
}

@Composable
fun HorizontalSpacerExtraLarge() {
    Spacer(modifier = Modifier.width(MaterialTheme.dimen.extra_large))
}