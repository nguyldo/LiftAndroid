package com.nguyendo.lift.ui.views.Workouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.ui.theme.TextType
import com.nguyendo.lift.ui.theme.getTextStyle
import com.nguyendo.lift.ui.viewmodel.CreateWorkoutViewModel
import com.nguyendo.lift.ui.viewmodel.PostAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseList(
    createWorkoutViewModel: CreateWorkoutViewModel,
    postAuthViewModel: PostAuthViewModel,
    dismissSheet: () -> Unit,
    addExercise: (exercise: ExerciseMapping) -> Unit
) {
    val exerciseList by postAuthViewModel.exerciseListState.collectAsState()

    ModalBottomSheet(onDismissRequest = dismissSheet) {
        LazyColumn {
            items(exerciseList.size) { index ->
                Row {
                    Column(
                        modifier = Modifier.clickable {
                            addExercise(exerciseList[index])
                        }
                    ) {
                        Text(exerciseList[index].name, style = getTextStyle(TextType.TITLE))
                        Text(exerciseList[index].focus, style = getTextStyle(TextType.BODY))
                    }
                }
            }
        }
    }
}