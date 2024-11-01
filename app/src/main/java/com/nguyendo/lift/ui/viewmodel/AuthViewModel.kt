package com.nguyendo.lift.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.nguyendo.lift.data.model.User
import com.nguyendo.lift.data.repo.AuthRepo
import com.nguyendo.lift.data.repo.UserRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.ArrayList
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepo: AuthRepo,
    private val userRemoteRepo: UserRemoteRepo
) : ViewModel() {
    fun user(): FirebaseUser? = authRepo.getUser()

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        authRepo.login(email, password)
            .addOnSuccessListener {
                Log.d("AuthViewModel", "login - success")
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.d("AuthViewModel", e.toString())
                onFailure()
            }
    }

    fun register(
        email: String,
        password: String,
        username: String,
        name: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        authRepo.register(email, password)
            .addOnSuccessListener {
                val user = User(
                    id = UUID.randomUUID().toString(),
                    email = email,
                    username = username,
                    name = name,
                    workouts = ArrayList()
                )
                try {
                    userRemoteRepo.addUserDetails(user)
                    onSuccess()
                } catch (e: Exception) {
                    TODO("show error")
                }
            }
            .addOnFailureListener { e ->
                Log.d("AuthViewModel", e.toString())
                onFailure
            }
    }

    fun signout() {
        authRepo.logout()
    }
}