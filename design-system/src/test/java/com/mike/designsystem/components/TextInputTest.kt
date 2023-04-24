package com.mike.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.mike.designsystem.theme.BaubapTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class TextInputTest {

    @get:Rule
    val paparazziRule: Paparazzi = Paparazzi()


    @Test
    fun testTextInputComponent(
        @TestParameter isDarkMode: Boolean
    ) {
        paparazziRule.snapshot {
            BaubapTheme(isDarkMode) {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier.padding(padding),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val passwordFocusRequester = FocusRequester()
                        val focusManager = LocalFocusManager.current
                        var valueName by remember { mutableStateOf("") }
                        var valuePassword by remember { mutableStateOf("") }
                        Column(
                            Modifier
                                .padding(24.dp)
                                .background(BaubapTheme.colors.background)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(
                                16.dp,
                                alignment = Alignment.Top
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            DSTextInput(
                                inputType = InputType.Name,
                                keyboardActions = KeyboardActions(onNext = {
                                    passwordFocusRequester.requestFocus()
                                }),
                                value = valueName,
                                onValueChange = { value ->
                                    valueName = value
                                }
                            )
                            DSTextInput(
                                inputType = InputType.Password,
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()
                                }),
                                focusRequester = passwordFocusRequester,
                                value = valuePassword,
                                onValueChange = { valueName ->
                                    valuePassword = valueName
                                }
                            )
                        }
                    }
                }
            }

        }
    }
}