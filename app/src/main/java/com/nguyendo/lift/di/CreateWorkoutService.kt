package com.nguyendo.lift.di

import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Workout
import kotlinx.coroutines.flow.MutableStateFlow

interface CreateWorkoutService {
    var workoutInProgress: MutableStateFlow<Boolean>
    var seconds: MutableStateFlow<Int>
    var exercises: MutableStateFlow<List<Exercise>>

    fun startWorkout()

    fun discardWorkout()

    fun endWorkout(workout: Workout, exercises: List<Exercise>)

    fun addExercise(exercise: ExerciseMapping)
}
