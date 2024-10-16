package com.nguyendo.lift.data.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.nguyendo.lift.data.model.User

interface UserRemoteRepo {
    fun getUserDetails(id: String): User?

    fun addUserDetails(user: User)
}