package com.nguyendo.lift.data.repo

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyendo.lift.data.model.ExerciseMapping
import com.nguyendo.lift.data.model.Measurement
import com.nguyendo.lift.data.model.Workout
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutsRemoteRepoImpl @Inject constructor(
    val firebaseFirestore: FirebaseFirestore
) : WorkoutsRemoteRepo {
    override suspend fun getWorkouts(ids: List<String>): List<Workout> {
        try {
            val ref = firebaseFirestore
                .collection(WORKOUTS)
                .whereIn("userId", ids)
                .get()
                .await()

            val workouts = ref.documents.mapNotNull { workout ->
                workout.toObject(Workout::class.java)
            }

            return workouts
        } catch (e: Exception) {
            Log.d("WorkoutsRemoteRepoImpl", e.toString())
            return emptyList()
        }
    }

    override suspend fun getExerciseList(): List<ExerciseMapping> {
        try {
            val ref = firebaseFirestore
                .collection(EXERCISE_NAME_MAPPING)
                .get()
                .await()

            val exercises = ref.documents.mapNotNull { exercise ->
                exercise.data?.let {
                    val measurements = (it.get("measurements") as List<Map<String, String>>).map { measurement ->
                        measurement.get("name") ?: ""
                    }
                    ExerciseMapping(
                        id = it.get("id") as String,
                        name = it.get("name") as String,
                        focus = it.get("focus") as String,
                        type = it.get("type") as String,
                        measurements = measurements
                    )
                }
            }

            return exercises
        } catch (e: Exception) {
            Log.d("WorkoutsRemoteRepoImpl", e.toString())
            return emptyList()
        }
    }
}

private const val WORKOUTS = "workouts"
private const val EXERCISE_NAME_MAPPING = "exerciseNameMapping"
