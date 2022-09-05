package klt.mdy.offlinesupportwithpaging.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.google.accompanist.coil.rememberCoilPainter
import klt.mdy.offlinesupportwithpaging.theme.dimen


enum class LeadingContentType(val type: Int) {
    ICON(0),
    AVATAR(1),
    IMAGE_URL(2)
}

enum class TrailingContentType(val type: Int) {
    ICON(0),
    CHECKBOX(1),
    RADIO_BUTTON(2),
    SWITCH(3),
    TEXT_BUTTON(4)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonListItem(
    modifier: Modifier,
    listContentColor: Color = MaterialTheme.colorScheme.onSurface,

    leadingContentType: LeadingContentType? = null,
    leadingIcon: Painter? = null,
    leadingIconSize: Dp = MaterialTheme.dimen.base_3x,
    leadingProfileAvatar: String = "",
    leadingProfileName: String = "",
    leadingAvatarSize: Dp = MaterialTheme.dimen.base_6x,
    leadingAvatarTextSize: TextStyle = MaterialTheme.typography.headlineLarge,
    leadingImageUrl: String = "",
    leadingImageSize: Dp = MaterialTheme.dimen.base_4x,

    headlineText: String = "",
    supportingText: String = "",
    supportingTextMaxLine: Int = 1,

    trailingContentType: TrailingContentType? = null,
    trailingIcon: Painter? = null,
    trailingIconSize: Dp = MaterialTheme.dimen.small_icon,
    trailingTextButtonLabel: String = "",
    trailingTextButtonLabelColor: Color = MaterialTheme.colorScheme.primary,

    onClickListItem: () -> Unit? = {},
    onClickTrailingIcon: () -> Unit? = {},
    onClickTrailingCheckbox: () -> Unit = {},
    onClickTrailingRadioButton: () -> Unit = {},
    onClickTrailingSwitch: (Boolean) -> Unit = {},
    onClickTrailingTextButton: () -> Unit = {},

    trailingCheckboxState: Boolean = false,
    trailingRadioButtonState: Boolean = false,
    trailingSwitchState: Boolean = false,
) {
    val image = rememberCoilPainter(
        request = leadingImageUrl,
        fadeIn = true
    )
    ListItem(
        modifier = modifier
            .clickable(
                onClick = { onClickListItem() }
            ),
        colors = ListItemDefaults.colors(
            leadingIconColor = listContentColor,
            headlineColor = listContentColor,
        ),
        leadingContent = {
            leadingContentType?.let {
                when (leadingContentType) {
                    LeadingContentType.ICON -> {
                        Icon(
                            modifier = modifier.size(leadingIconSize),
                            painter = leadingIcon!!,
                            contentDescription = "Leading Icon",
                        )
                    }
                    LeadingContentType.AVATAR -> {
                        if (leadingProfileAvatar.isNotEmpty()) {
                          /*  AvatarUrl(
                                url = leadingProfileAvatar,
                                size = leadingAvatarSize,
                                onClickedAvatarImage = {}
                            )*/
                        } else {
                            if (leadingProfileName.isNotEmpty()) {
                             /*   AvatarText(
                                    name = leadingProfileName.first(),
                                    size = leadingAvatarSize,
                                    textSize = leadingAvatarTextSize,
                                    onClickedAvatarImage = {}
                                )*/
                            }
                        }
                    }
                    LeadingContentType.IMAGE_URL -> {
                        Image(
                            modifier = modifier
                                .width(leadingImageSize),
                            painter = image,
                            contentDescription = "Leading Image",
                        )
                    }
                }
            }
        },
        headlineText = {
            if (headlineText.isNotEmpty()) {
                Text(text = headlineText)
            }
        },
        supportingText = {
            if (supportingText.isNotEmpty()) {
                Text(
                    text = supportingText,
                    maxLines = supportingTextMaxLine,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        trailingContent = {
            trailingContentType?.let {
                when (trailingContentType) {
                    TrailingContentType.ICON -> {
                        IconButton(onClick = { onClickTrailingIcon() }) {
                            Icon(
                                modifier = modifier.size(trailingIconSize),
                                painter = trailingIcon!!,
                                contentDescription = "Leading Icon",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    TrailingContentType.CHECKBOX -> {
                        Checkbox(
                            checked = trailingCheckboxState,
                            onCheckedChange = { onClickTrailingCheckbox() },
                        )
                    }
                    TrailingContentType.RADIO_BUTTON -> {
                        RadioButton(
                            selected = trailingRadioButtonState,
                            onClick = { onClickTrailingRadioButton() })
                    }
                    TrailingContentType.SWITCH -> {
                        Switch(
                            checked = trailingSwitchState,
                            onCheckedChange = onClickTrailingSwitch
                        )
                    }
                    TrailingContentType.TEXT_BUTTON -> {
                        TextButton(onClick = { onClickTrailingTextButton() }) {
                            Text(
                                text = trailingTextButtonLabel,
                                color = trailingTextButtonLabelColor
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview
private fun Preview() {
    Surface() {
        CommonListItem(
            modifier = Modifier,
            leadingContentType = LeadingContentType.AVATAR,
            leadingProfileName = "Wai Phyo",
            leadingAvatarSize = MaterialTheme.dimen.base_5x,
            headlineText = "Headline Text",
            supportingText = "Blah Blah Bio Text goes here...",
            trailingContentType = TrailingContentType.SWITCH,
            onClickTrailingCheckbox = {}
        )
    }
}