package com.nguyendo.lift.data.repo

import com.nguyendo.lift.data.model.Workout

interface WorkoutsRemoteRepo {
    suspend fun getWorkouts(ids: List<String>): List<Workout>
    // suspend fun getExercises(ids: ArrayList<String>)
}