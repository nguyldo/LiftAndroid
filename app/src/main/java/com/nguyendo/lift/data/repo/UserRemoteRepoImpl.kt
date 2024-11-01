package com.nguyendo.lift.data.repo

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyendo.lift.data.model.User
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRemoteRepoImpl @Inject constructor(
    val firebaseFirestore: FirebaseFirestore
) : UserRemoteRepo {
    override suspend fun getUserDetails(id: String): User {
        try {
            val ref = firebaseFirestore.collection(USERS).document(id).get().await()
            val user = ref.toObject(User::class.java)
            return when {
                user != null -> user
                else -> throw Exception("no user found")
            }
        } catch (e: Exception) {
            Log.d("UserRemoteRepoImpl", e.toString())
            throw e
        }
    }

    override fun addUserDetails(user: User) {
        firebaseFirestore.collection(USERS).add(user)
            .addOnFailureListener { e ->
                throw e
            }
    }
}

private const val USERS = "users"
