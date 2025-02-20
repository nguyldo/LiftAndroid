package com.nguyendo.lift.ui.views.Workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Measurement
import com.nguyendo.lift.ui.components.ExerciseCell
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle
import com.nguyendo.lift.ui.viewmodel.CreateWorkoutViewModel
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel

@Composable
fun CreateWorkout(
    createWorkoutViewModel: CreateWorkoutViewModel,
    postAuthViewModel: PostAuthViewModel,
    dismissScreen: () -> Unit
) {
    val seconds by createWorkoutViewModel.secondsState.collectAsState()
    var showExerciseListSheet by remember { mutableStateOf(false) }
    val exercises: List<Exercise> by createWorkoutViewModel.exercisesState.collectAsState()
    val exerciseList by postAuthViewModel.exerciseListState.collectAsState()

    LaunchedEffect(Unit) {
        createWorkoutViewModel.startWorkout()
    }

    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(createWorkoutViewModel.formatSecondsToTime(seconds), style = getTextStyle(TextType.BODY))
            Button(onClick = {
                showExerciseListSheet = true
            }) {
                Text("Add Exercise", style = getTextStyle(TextType.BODY))
            }
            Button(onClick = {
                createWorkoutViewModel.discardWorkout()
                    .also { dismissScreen() }
            }) {
                Text("\uD83D\uDDD1\uFE0F", style = getTextStyle(TextType.BODY))
            }
        }

        LazyColumn {
            items(exercises.size) { index ->
                val measurements: List<Measurement> = (exerciseList.filter { it.id == exercises[index].exerciseId })[0].measurements
                ExerciseCell(exercises[index], index, createWorkoutViewModel, measurements)
            }
        }

        if (showExerciseListSheet) {
            ExerciseList(
                createWorkoutViewModel,
                postAuthViewModel,
                { showExerciseListSheet = false },
                { exercise ->
                    createWorkoutViewModel.addExercise(exercise = exercise)
                }
            )
        }
    }
}