package com.nguyendo.lift.ui.views.Workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nguyendo.lift.ui.components.WorkoutCell
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel

@Composable
fun FeedView(
    postAuthViewModel: PostAuthViewModel,
    userId: String
) {
    LaunchedEffect(userId) {
        postAuthViewModel.fetchWorkouts(userId)
        postAuthViewModel.fetchExercisesList()
    }

    val workoutsState by postAuthViewModel.workoutsState.collectAsState()

    LazyColumn(userScrollEnabled = true) {
        items(workoutsState.size) { index ->
            WorkoutCell(workoutsState[index])
        }
    }
}