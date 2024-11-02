package com.nguyendo.lift.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nguyendo.lift.data.model.User
import com.nguyendo.lift.data.repo.AuthRepo
import com.nguyendo.lift.data.repo.UserRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.nguyendo.lift.data.model.Workout
import com.nguyendo.lift.data.repo.WorkoutsRemoteRepo
import kotlinx.coroutines.launch

@HiltViewModel
class PostAuthViewModel @Inject constructor(
    private val authRepo: AuthRepo,
    private val userRemoteRepo: UserRemoteRepo,
    private val workoutsRemoteRepo: WorkoutsRemoteRepo
) : ViewModel() {
    private val _userDetailsState = MutableStateFlow<User?>(null)
    val userDetailsState: StateFlow<User?> = _userDetailsState

    private val _workoutsState = MutableStateFlow<List<Workout>>(emptyList())
    val workoutsState: StateFlow<List<Workout>> = _workoutsState

    fun fetchUserDetails(userId: String) {
        viewModelScope.launch {
            try {
                val userDetails = userRemoteRepo.getUserDetails(userId)
                _userDetailsState.value = userDetails
            } catch (e: Exception) {
                Log.d("PostAuthViewModel", e.toString())
                _userDetailsState.value = null
            }
        }
    }

    fun fetchWorkouts(userId: String) {
        viewModelScope.launch {
            try {
                val workouts =
                    workoutsRemoteRepo
                        .getWorkouts(listOf(userId))
                        .sortedByDescending {
                            it.timestamp
                        }
                _workoutsState.value = workouts
                Log.d("PostAuthViewModel", workouts.toString())
            } catch (e: Exception) {
                Log.d("PostAuthViewModel", e.toString())
                _workoutsState.value = emptyList()
            }
        }
    }
}