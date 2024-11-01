package com.nguyendo.lift.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TabBar(navController: NavController) {
    val tabs = listOf(
        Tab.Feed,
        Tab.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Color.White
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                label = {
                    Text(tab.label)
                },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color.LightGray
                )
            )
        }
    }
}

sealed class Tab(
    val route: PostAuthScreens,
    val label: String,
    val icon: ImageVector
) {
    object Feed : Tab(
        route = PostAuthScreens.Feed,
        label = "Workouts",
        icon = Icons.Default.Home
    )

    object Profile : Tab(
        route = PostAuthScreens.Profile,
        label = "Profile",
        icon = Icons.Default.AccountCircle
    )
}