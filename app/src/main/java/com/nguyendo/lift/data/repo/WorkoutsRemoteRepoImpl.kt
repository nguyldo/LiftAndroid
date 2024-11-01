package com.nguyendo.lift.data.repo

import android.util.Log
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
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
}

private const val WORKOUTS = "workouts"