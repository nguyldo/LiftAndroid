package com.nguyendo.lift.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.nguyendo.lift.ui.nav.PostAuthScreens.Feed
import com.nguyendo.lift.ui.nav.PostAuthScreens.Profile
import com.nguyendo.lift.ui.views.FeedView
import com.nguyendo.lift.ui.views.ProfileView

@Composable
fun PostAuthNavGraph(logout: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            TabBar(navController)
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = Feed
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
}