package com.nguyendo.lift.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.nguyendo.lift.data.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    fun user(): FirebaseUser? = authRepo.getUser()

    fun login(
        email: String,
        password: String,
    ) {
        authRepo.login(email, password)
            .addOnSuccessListener {
                TODO("nav to post-auth")
            }
            .addOnFailureListener {
                TODO("failure message")
            }
    }

    fun register(
        email: String,
        password: String,
        username: String,
        name: String,
    ) {
        authRepo.register(email, password)
            .addOnSuccessListener {
                TODO("add user to database and nav to post-auth")
            }
            .addOnFailureListener {
                TODO("failure message")
            }
    }
}