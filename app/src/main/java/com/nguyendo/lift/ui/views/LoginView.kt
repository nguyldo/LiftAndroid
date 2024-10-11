package com.nguyendo.lift.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginView() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            "Hello world",
            modifier = Modifier.padding(innerPadding)
        )
    }
}