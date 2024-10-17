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

@Serializable
sealed class PostAuthScreens {
    @Serializable
    object Feed : PostAuthScreens()

    @Serializable
    object Profile : PostAuthScreens()
}
