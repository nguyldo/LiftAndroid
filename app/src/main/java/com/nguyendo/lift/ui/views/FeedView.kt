package com.nguyendo.lift.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.nguyendo.lift.ui.components.WorkoutCell
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel

@Composable
fun FeedView(
    postAuthViewModel: PostAuthViewModel,
    userId: String
) {
    LaunchedEffect(userId) {
        postAuthViewModel.fetchWorkouts(userId)
    }

    val workoutsState by postAuthViewModel.workoutsState.collectAsState()

    LazyColumn {
        items(workoutsState.size) { index ->
            WorkoutCell(workoutsState.get(index))
        }
    }
}