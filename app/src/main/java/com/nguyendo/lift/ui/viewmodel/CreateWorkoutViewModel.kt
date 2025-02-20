package com.nguyendo.lift.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Set
import com.nguyendo.lift.data.model.Workout
import com.nguyendo.lift.data.repo.WorkoutsRemoteRepo
import com.nguyendo.lift.di.CreateWorkoutService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutViewModel @Inject constructor(
    private val workoutsRemoteRepo: WorkoutsRemoteRepo,
    private val createWorkoutService: CreateWorkoutService
) : ViewModel() {
    val workoutInProgressState = createWorkoutService.workoutInProgress
    val secondsState = createWorkoutService.seconds
    val exercisesState = createWorkoutService.exercises

    fun startWorkout() {
        createWorkoutService.startWorkout()
    }

    fun discardWorkout() {
        createWorkoutService.discardWorkout()
    }

    fun endWorkout() {
        createWorkoutService.endWorkout(Workout(), listOf())
    }

    fun formatSecondsToTime(time: Int): String {
        val hours = time / 3600
        val mins = (time - (hours * 3600)) / 60
        val seconds = (time - (hours * 3600) - (mins * 60))
        return when (hours) {
            0 -> String.format("%02d:%02d", mins, seconds)
            else -> String.format("%02d:%02d", hours, mins)
        }
    }

    fun addExercise(exercise: ExerciseMapping) {
        createWorkoutService.addExercise(exercise)
    }

    fun addSet(exerciseIndex: Int) {
        val newExerciseState = exercisesState.value.toMutableList()
        val newSets = newExerciseState[exerciseIndex].sets + listOf(Set())
        newExerciseState[exerciseIndex] = newExerciseState[exerciseIndex].copy(sets = newSets)
        exercisesState.value = newExerciseState.toList()
    }

    fun updateSet(
        exerciseIndex: Int,
        setIndex: Int,
        rep: Int? = null,
        weight: Int? = null,
        distance: Float? = null,
        time: Int? = null,
        incline: Float? = null
    ) {
        val newExerciseState = exercisesState.value.toMutableList()
        var updatedSets = newExerciseState[exerciseIndex].sets
        if (rep != null) {
            updatedSets[setIndex].rep = rep
        }
        newExerciseState[exerciseIndex] = newExerciseState[exerciseIndex].copy(sets = updatedSets)
        exercisesState.value = newExerciseState.toList()
    }
}