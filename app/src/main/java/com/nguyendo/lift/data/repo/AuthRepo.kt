package com.nguyendo.lift.data.repo

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepo {
    /**
     * Get user
     *
     * @return FirebaseUser or null if a user isn't signed in
     */
    fun getUser(): FirebaseUser?

    /**
     * Login through firebase auth
     *
     * @param email user's email
     * @param password user's  password
     */
    fun login(
        email: String,
        password: String,
    ): Task<AuthResult>

    /**
     * Register through firebase auth
     *
     * @param email user's email
     * @param password user's password
     * @param name user's name
     * @param username user's username
     */
    fun register(
        email: String,
        password: String,
    ): Task<AuthResult>

    fun logout()
}