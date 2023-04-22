package com.mike.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mike.designsystem.icons.Icons
import com.mike.designsystem.icons.Lock
import com.mike.designsystem.icons.User
import com.mike.designsystem.theme.BaubapTheme
import com.mike.designsystem.theme.md_theme_dark_background

sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name : InputType(
        label = "Username",
        icon = Icons.User,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Password",
        icon = Icons.Lock,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun DSTextInput(
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = value,
        onValueChange = { newText: TextFieldValue ->
            value = newText
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        shape = BaubapTheme.shapes.radius200,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = BaubapTheme.colors.background,
            textColor = BaubapTheme.colors.onBackground,
            placeholderColor = BaubapTheme.colors.primary,
            focusedBorderColor = BaubapTheme.colors.primary,
            unfocusedBorderColor = BaubapTheme.colors.outline,
            focusedLabelColor = BaubapTheme.colors.primary,
            unfocusedLabelColor = BaubapTheme.colors.secondary,
        ),
        textStyle = BaubapTheme.typography.text2,
        singleLine = true,
        keyboardOptions = inputType.keyboardOptions,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions,
        label = {
            Text(text = inputType.label)
        },
        placeholder = {
            Icon(
                imageVector = inputType.icon,
                null
            )
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDarkTextInput() {
    BaubapTheme {
        val passwordFocusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current
        Column(
            Modifier
                .padding(24.dp)
                .background(BaubapTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DSTextInput(
                inputType = InputType.Name,
                keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                })
            )
            DSTextInput(
                inputType = InputType.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                focusRequester = passwordFocusRequester
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLightTextInput() {
    BaubapTheme {
        val passwordFocusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current
        Column(
            Modifier
                .padding(24.dp)
                .background(BaubapTheme.colors.background)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DSTextInput(
                inputType = InputType.Name,
                keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                })
            )
            DSTextInput(
                inputType = InputType.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                focusRequester = passwordFocusRequester
            )
        }
    }
}