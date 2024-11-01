package com.nguyendo.lift.data.model

import java.util.Date

data class User(
    val id: String = "",
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val workouts: ArrayList<String> = ArrayList()
)

data class Workout(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val description: String = "",
    val timestamp: Date = Date(),
    val minutes: Int = 0,
    val exercises: ArrayList<String> = ArrayList(),
    val perceivedDifficulty: Int = 0
)