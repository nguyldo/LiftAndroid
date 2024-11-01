package com.nguyendo.lift.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nguyendo.lift.data.model.User
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel

@Composable
fun ProfileView(
    postAuthViewModel: PostAuthViewModel,
    logout: () -> Unit,
    userId: String
) {
    LaunchedEffect(userId) {
        postAuthViewModel.fetchUserDetails(userId)
    }

    val userDetailsState by postAuthViewModel.userDetailsState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        userDetailsState?.let { ProfileViewContent(logout, it) } ?: ProfileViewLoading()
    }
}

@Composable
fun ProfileViewLoading() {
    CircularProgressIndicator()
}

@Composable
fun ProfileViewContent(logout: () -> Unit, user: User) {
    Column(
        modifier = Modifier.fillMaxSize().padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(user.username, style = getTextStyle(TextType.TITLE))
            Spacer(modifier = Modifier.size(36.dp))
            Text(user.name, style = getTextStyle(TextType.TITLE))
        }

        Button(onClick = logout) {
            Text("Logout", style = getTextStyle(TextType.BODY))
        }
    }
}