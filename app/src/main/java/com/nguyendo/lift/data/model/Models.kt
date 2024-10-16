package com.nguyendo.lift.data.model

data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val workouts: List<String>
)
