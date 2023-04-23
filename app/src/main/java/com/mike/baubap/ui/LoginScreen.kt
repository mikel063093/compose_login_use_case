package com.mike.baubap.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mike.designsystem.components.ButtonStyle
import com.mike.designsystem.components.DSButton
import com.mike.designsystem.components.DSTextInput
import com.mike.designsystem.components.InputType
import com.mike.designsystem.icons.Icons
import com.mike.designsystem.icons.User
import com.mike.designsystem.theme.BaubapTheme

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    val areInputsValid by loginViewModel.areInputsValid.collectAsStateWithLifecycle()
    val name by loginViewModel.userName.collectAsStateWithLifecycle()
    val pass by loginViewModel.password.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding()
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DSTextInput(
            inputType = InputType.Name,
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequester.requestFocus()
                }
            ),
            value = name.value,
            onValueChange = loginViewModel::updateName
        )
        DSTextInput(
            inputType = InputType.Password,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            focusRequester = passwordFocusRequester,
            value = pass.value,
            onValueChange = loginViewModel::updatePassword
        )
        DSButton(
            onClick = {},
            buttonStyle = ButtonStyle.ButtonPrimary,
            text = "Login",
            enabled = areInputsValid,
            imageVector = Icons.User
        )
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    BaubapTheme {
        Box(
            modifier = Modifier
                .systemBarsPadding()
                .background(BaubapTheme.colors.background)
        ) {
            Box(Modifier.imePadding()) {
                LoginScreen()
            }
        }
    }
}