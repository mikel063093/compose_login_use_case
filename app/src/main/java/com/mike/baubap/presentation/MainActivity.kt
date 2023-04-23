package com.mike.baubap.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import com.mike.baubap.presentation.login.LoginScreen
import com.mike.designsystem.theme.BaubapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
    }
}