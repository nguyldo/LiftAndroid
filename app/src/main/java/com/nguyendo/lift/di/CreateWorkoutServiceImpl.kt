package com.nguyendo.lift.di

import android.os.Handler
import android.os.Looper
import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Set
import com.nguyendo.lift.data.model.Workout
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class CreateWorkoutServiceImpl : CreateWorkoutService {
    override var workoutInProgress = MutableStateFlow(false)
    override var seconds = MutableStateFlow(0)
    override var exercises = MutableStateFlow<List<Exercise>>(emptyList())

    private val handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            if (workoutInProgress.value) {
                seconds.value++
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun startWorkout() {
        if (!workoutInProgress.value) {
            handler.postDelayed(runnable, 1000)
            workoutInProgress.value = true
        }
    }

    override fun discardWorkout() {
        if (workoutInProgress.value) {
            handler.removeCallbacks(runnable)
            workoutInProgress.value = false
            seconds.value = 0
            exercises.value = emptyList()
        }
    }

    override fun endWorkout(workout: Workout, exercises: List<Exercise>) {
        if (workoutInProgress.value) {
            handler.removeCallbacks(runnable)
            workoutInProgress.value = false
        }
    }

    override fun addExercise(exercise: ExerciseMapping) {
        exercises.value += listOf(
            Exercise(
                id = UUID.randomUUID().toString(),
                exerciseId = exercise.id,
                exerciseName = exercise.name,
                sets = listOf(Set()),
                unitWeight = if (exercise.measurements.contains("weight")) "lbs" else null,
                unitDistance = if (exercise.measurements.contains("distance")) "miles" else null,
            )
        )
    }

    /*
    override fun addExercise(exercise: ExerciseMapping) {
        exercises.value += listOf(
            Exercise(
                id = UUID.randomUUID().toString(),
                exerciseId = exercise.id,
                exerciseName = exercise.name,
                reps = if (exercise.measurements.contains("reps")) emptyList() else null,
                weight = if (exercise.measurements.contains("weight")) emptyList() else null,
                distance = if (exercise.measurements.contains("distance")) emptyList() else null,
                time = if (exercise.measurements.contains("time")) emptyList() else null,
                incline = if (exercise.measurements.contains("incline")) emptyList() else null,
                unitWeight = if (exercise.measurements.contains("weight")) "lbs" else null,
                unitDistance = if (exercise.measurements.contains("distance")) "miles" else null,
            )
        )
    }*/
}