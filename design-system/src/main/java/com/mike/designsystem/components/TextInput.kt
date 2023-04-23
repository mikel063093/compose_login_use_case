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
import androidx.compose.runtime.remember
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mike.designsystem.icons.Icons
import com.mike.designsystem.icons.Lock
import com.mike.designsystem.icons.User
import com.mike.designsystem.theme.BaubapTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 *Sealed class representing different input types.
 *@param label the label for the input type
 *@param icon the icon for the input type
 *@param keyboardOptions the keyboard options for the input type
 *@param visualTransformation the visual transformation for the input type
 */
sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    /**
     *Input type representing a username.
     */
    object Name : InputType(
        label = "Username",
        icon = Icons.User,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        visualTransformation = VisualTransformation.None
    )

    /**
     *Input type representing a password.
     */
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

/**
 *Composable function for rendering a text input with the specified [InputType],
 *[focusRequester], and [keyboardActions].
 *@param inputType the input type for the text field
 *@param focusRequester the focus requester for the text field
 *@param keyboardActions the keyboard actions for the text field
 */
@Composable
fun DSTextInput(
    valueStateFlow: String,
    onValueChange: (value: String) -> Unit,
    inputType: InputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(
        value = valueStateFlow,
        onValueChange = onValueChange,
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


/*
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDarkTextInput() {
    BaubapTheme {
        val passwordFocusRequester = FocusRequester()
        val focusManager = LocalFocusManager.current
        val valueName = remember {
            MutableStateFlow(TextFieldValue(""))
        }
        val valuePassword = remember { MutableStateFlow(TextFieldValue("")) }
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
                }),
                valueStateFlow = valueName,
                onValueChange = { value ->
                    valueName.value = value
                }
            )
            DSTextInput(
                inputType = InputType.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                focusRequester = passwordFocusRequester,
                valueStateFlow = valuePassword,
                onValueChange = { valueName ->
                    valuePassword.value = valueName
                }
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
        val valueName = remember { MutableStateFlow(TextFieldValue("")) }
        val valuePassword = remember { MutableStateFlow(TextFieldValue("")) }
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
                }),
                valueStateFlow = valueName,
                onValueChange = { value ->
                    valueName.value = value
                }
            )
            DSTextInput(
                inputType = InputType.Password,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                focusRequester = passwordFocusRequester,
                valueStateFlow = valuePassword,
                onValueChange = { valueName ->
                    valuePassword.value = valueName
                }
            )
        }
    }
}
*/
