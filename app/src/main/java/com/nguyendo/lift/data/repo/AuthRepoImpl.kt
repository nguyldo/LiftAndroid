package com.nguyendo.lift.data.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepoImpl
    @Inject
    constructor(
        private val firebaseAuth: FirebaseAuth,
    ) : AuthRepo {
        override fun getUser(): FirebaseUser? = firebaseAuth.currentUser

        override fun login(
            email: String,
            password: String,
        ): Task<AuthResult> = firebaseAuth.signInWithEmailAndPassword(email, password)

        override fun register(
            email: String,
            password: String,
        ): Task<AuthResult> = firebaseAuth.createUserWithEmailAndPassword(email, password)

    override fun logout() = firebaseAuth.signOut()
}