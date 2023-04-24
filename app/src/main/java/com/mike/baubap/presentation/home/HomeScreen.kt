package com.mike.baubap.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mike.designsystem.components.DSButton
import com.mike.designsystem.theme.BaubapTheme

@Composable
fun HomeScreen(
    userName: () -> String,
    popUpToLogin: () -> Unit,
) {
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
        Text(
            text = "Welcome back, ${userName.invoke()}",
            style = BaubapTheme.typography.h5,
            color = BaubapTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.size(32.dp))
        DSButton(onClick = popUpToLogin, text = { "Go back" })
    }
}