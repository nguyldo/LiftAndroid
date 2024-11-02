package com.nguyendo.lift.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.nguyendo.lift.R
import com.nguyendo.lift.ui.nav.PostAuthScreens.Workouts
import com.nguyendo.lift.ui.nav.PostAuthScreens.Profile
import com.nguyendo.lift.ui.nav.WorkoutsScreens.WorkoutsHome
import com.nguyendo.lift.ui.nav.WorkoutsScreens.WorkoutDetails
import com.nguyendo.lift.ui.nav.WorkoutsScreens.CreateWorkout
import com.nguyendo.lift.ui.nav.ProfileScreens.ProfileHome
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel
import com.nguyendo.lift.ui.views.Workouts.FeedView
import com.nguyendo.lift.ui.views.Profile.ProfileView
import com.nguyendo.lift.ui.views.Workouts.CreateWorkout
import com.nguyendo.lift.ui.views.Workouts.WorkoutDetailsView

@Composable
fun PostAuthNavGraph(
    logout: () -> Unit,
    userId: String
) {
    val navController = rememberNavController()
    val postAuthViewModel: PostAuthViewModel = hiltViewModel<PostAuthViewModel>()
    var topBarTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBar(navController, topBarTitle)
        },
        bottomBar = {
            TabBar(navController)
        },
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            NavHost(
                navController = navController,
                startDestination = Workouts,
            ) {
                navigation<Workouts>(startDestination = WorkoutsHome) {
                    composable<WorkoutsHome> {
                        topBarTitle = stringResource(R.string.workouts_title)
                        FeedView(postAuthViewModel, userId)
                    }
                    composable<CreateWorkout> {
                        topBarTitle = stringResource(R.string.create_workout_title)
                        CreateWorkout()
                    }
                    composable<WorkoutDetails> {
                        WorkoutDetailsView()
                    }
                }
                navigation<Profile>(startDestination = ProfileHome) {
                    composable<ProfileHome> {
                        topBarTitle = stringResource(R.string.profile_title)
                        ProfileView(postAuthViewModel, logout, userId)
                    }
                }
            }
        }
    }
}