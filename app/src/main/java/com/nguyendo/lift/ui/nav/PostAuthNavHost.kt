package com.nguyendo.lift.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.nguyendo.lift.ui.nav.PostAuthScreens.Feed
import com.nguyendo.lift.ui.nav.PostAuthScreens.Profile
import com.nguyendo.lift.ui.views.FeedView
import com.nguyendo.lift.ui.views.ProfileView

@Composable
fun PostAuthNavHost(logout: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            TabBar(navController)
        },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Feed,
            modifier = Modifier.padding(padding),
        ) {
            composable<Feed> {
                FeedView()
            }
            composable<Profile> {
                ProfileView(logout)
            }
        }
    }
}