package com.nguyendo.lift.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileView(logout: () -> Unit) {
    Column {
        Text("Profile")
        Button(onClick = logout) {
            Text("Logout")
        }
    }
}