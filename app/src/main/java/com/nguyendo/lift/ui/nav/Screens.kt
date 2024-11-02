package com.nguyendo.lift.ui.nav

import kotlinx.serialization.Serializable

/**
 * Screens
 *
 * Add screens here to be used in the NavHost
 */

sealed class AuthScreens {
    @Serializable
    object Onboarding

    @Serializable
    object Login

    @Serializable
    object Register

    @Serializable
    object PostAuth
}

sealed class PostAuthScreens {
    @Serializable
    object Workouts : PostAuthScreens()

    @Serializable
    object Profile : PostAuthScreens()
}

sealed class WorkoutsScreens {
    @Serializable
    object WorkoutsHome : WorkoutsScreens()

    @Serializable
    object WorkoutDetails : WorkoutsScreens()

    @Serializable
    object CreateWorkout : WorkoutsScreens()
}

sealed class ProfileScreens {
    @Serializable
    object ProfileHome : ProfileScreens()
}
