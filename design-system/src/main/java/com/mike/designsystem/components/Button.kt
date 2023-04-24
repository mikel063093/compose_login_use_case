package com.mike.designsystem.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mike.designsystem.icons.Icons
import com.mike.designsystem.icons.User
import com.mike.designsystem.theme.BaubapTheme


/**
 *The ButtonColor interface provides methods to determine the background and content color of a button,
 *based on whether the button is enabled or not.
 */
interface ButtonColor {

    /**
     * Represents the background color for this button, depending on [enabled].
     * @param enabled whether the button is enabled
     */
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    /**
     * Represents the content color for this button, depending on [enabled].
     * @param enabled whether the button is enabled
     */
    @Composable
    fun contentColor(enabled: Boolean): State<Color>
}

/**
 *The ButtonColorImpl class represents an immutable implementation of the ButtonColor interface,
 *which provides methods to determine the background and content color of a button, based on whether
 *the button is enabled or not.
 *This class is annotated with @Immutable to indicate that its state does not change after construction.
 *@param backgroundColor The background color of the button.
 *@param contentColor The content color of the button.
 *@param disabledBackgroundColor The background color of the button when it is disabled.
 *@param disabledContentColor The content color of the button when it is disabled.
 */
@Immutable
private class ButtonColorImpl(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) : ButtonColor {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }
}

/**
 *The ButtonStyle interface represents a sealed interface for defining different styles for a button.
 *It provides a single method, getButtonColor(), which returns a ButtonColor object representing the color
 *scheme for the button style.
 */
sealed interface ButtonStyle {
    /**
     * Returns the ButtonColor object representing the color scheme for this button style.
     * @return A ButtonColor object representing the color scheme for this button style.
     */
    @Composable
    fun getButtonColor(): ButtonColor

    /**
     *The ButtonPrimary object represents a button style with primary color scheme.
     *It implements the getButtonColor() method to return a ButtonColorImpl object with primary color values.
     */
    object ButtonPrimary : ButtonStyle {
        @Composable
        override fun getButtonColor(): ButtonColor {
            return ButtonColorImpl(
                backgroundColor = BaubapTheme.colors.primary,
                contentColor = BaubapTheme.colors.onPrimary,
                disabledBackgroundColor = BaubapTheme.colors.secondary,
                disabledContentColor = BaubapTheme.colors.onSecondary
            )
        }
    }
}

/**
 *The DSButton function is a composable function that creates a button with a specified style, text, and icon.
 *@param onClick The function to be executed when the button is clicked.
 *@param modifier The modifier for this button.
 *@param buttonStyle The style for this button. Defaults to ButtonStyle.ButtonPrimary if not specified.
 *@param enabled Whether the button is enabled. Defaults to true.
 *@param text The text to display on the button.
 *@param imageVector The icon to display on the button.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
@Suppress("LongParameterList")
fun DSButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonStyle: ButtonStyle = ButtonStyle.ButtonPrimary,
    enabled: Boolean = true,
    text: String,
    imageVector: ImageVector? = null
) {
    val contentColor by buttonStyle.getButtonColor().contentColor(enabled)
    val backgroundColor by buttonStyle.getButtonColor().backgroundColor(enabled)
    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        onClick = onClick,
        modifier = modifier
            .semantics { role = Role.Button }
            .height(40.dp)
            .fillMaxWidth(),
        enabled = enabled,
        color = backgroundColor,
        contentColor = contentColor.copy(alpha = 1f),
        interactionSource = interactionSource,
        shape = BaubapTheme.shapes.radius400
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = {
                imageVector?.let {
                    Icon(
                        modifier = Modifier.size(size = 16.dp),
                        imageVector = imageVector,
                        contentDescription = null,
                        tint = contentColor
                    )
                }
                Spacer(modifier = Modifier.size(size = 8.dp))
                Text(
                    text = text,
                    fontStyle = BaubapTheme.typography.body2.fontStyle,
                    color = contentColor
                )
            }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun previewDarkDsButton() {
    BaubapTheme {
        DSButton(
            onClick = { /*TODO*/ },
            text = "Login",
            imageVector = Icons.User,
            enabled = true
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun previewLightDsButton() {
    BaubapTheme {
        DSButton(
            onClick = { /*TODO*/ },
            text = "Login",
            imageVector = Icons.User,
            enabled = true
        )
    }
}