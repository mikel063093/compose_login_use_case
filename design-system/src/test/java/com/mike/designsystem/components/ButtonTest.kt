package com.mike.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.mike.designsystem.theme.BaubapTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class ButtonTest {

    @get:Rule
    val paparazziRule: Paparazzi = Paparazzi()


    @Test
    fun testButtonComponent(
        @TestParameter isDarkMode: Boolean
    ) {
        paparazziRule.snapshot {
            BaubapTheme(isDarkMode) {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier.padding(padding),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DSButton(
                            onClick = { /*TODO*/ },
                            text = "Login",
                            enabled = true
                        )
                    }

                }
            }

        }
    }
}