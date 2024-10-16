package com.nguyendo.lift.data.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.nguyendo.lift.data.model.User
import javax.inject.Inject

class UserRemoteRepoImpl @Inject constructor(
    val firebaseFirestore: FirebaseFirestore
) : UserRemoteRepo {
    override fun getUserDetails(id: String): User? {
        var user: User? = null
        firebaseFirestore.collection(USERS).document(id).get()
            .addOnSuccessListener { result ->
                user = result.toObject(User::class.java)
            }

        return user
    }

    override fun addUserDetails(user: User) {
        firebaseFirestore.collection(USERS).add(user)
            .addOnFailureListener { e ->
                throw e
            }
    }
}

private const val USERS = "users"
