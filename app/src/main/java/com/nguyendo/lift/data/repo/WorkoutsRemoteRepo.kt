package com.nguyendo.lift.data.repo

import com.nguyendo.lift.data.model.Exercise
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Workout

interface WorkoutsRemoteRepo {
    suspend fun getWorkouts(ids: List<String>): List<Workout>
    suspend fun getExerciseList(): List<ExerciseMapping>
    // suspend fun getExercises(ids: ArrayList<String>)
}