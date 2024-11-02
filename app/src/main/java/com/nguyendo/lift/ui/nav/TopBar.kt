package com.nguyendo.lift.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    title: String
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val showBackButton: Boolean = currentBackStackEntry?.destination?.route?.let {
        !(it.contains("WorkoutsHome") || it.contains("ProfileHome"))
    } ?: false
    val showCreateWorkoutButton: Boolean = currentBackStackEntry?.destination?.route?.let {
        it.contains("WorkoutsHome")
    } ?: false

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(),
        title = { Text(title) },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        } else { { } },
        actions = if (showCreateWorkoutButton) {
            {
                IconButton(onClick = { navController.navigate(WorkoutsScreens.CreateWorkout) }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "new workout"
                    )
                }
            }
        } else { { } }
    )
}