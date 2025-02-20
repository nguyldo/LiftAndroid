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

/*
data class Exercise(
    val id: String = "",
    val exerciseId: String = "",
    val exerciseName: String = "",
    val reps: List<Int>? = null,
    val weight: List<Int>? = null,
    val distance: List<Float>? = null,
    val time: List<Int>? = null,
    val incline: List<Float>? = null,
    val unitWeight: String? = null,
    val unitDistance: String? = null,
    val numSets: Int = 0
)*/

data class Exercise(
    val id: String = "",
    val exerciseId: String = "",
    val exerciseName: String = "",
    var sets: List<Set> = emptyList(),
    val unitWeight: String? = null,
    val unitDistance: String? = null
)

data class ExerciseMapping(
    val id: String = "",
    val name: String = "",
    val focus: String = "",
    val type: String = "",
    val measurements: List<Measurement> = emptyList()
)

typealias Measurement = String

/*
data class Measurement(
    val name: String,
    val valueType: String
)*/

data class Set(
    var rep: Int? = null,
    var weight: Int? = null,
    var distance: Float? = null,
    var time: Int? = null,
    var incline: Float? = null,
)