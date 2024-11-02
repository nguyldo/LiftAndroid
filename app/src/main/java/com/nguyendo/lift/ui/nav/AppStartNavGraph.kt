package com.nguyendo.lift.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nguyendo.lift.ui.viewmodel.AuthViewModel
import androidx.navigation.compose.composable
import com.nguyendo.lift.ui.views.Auth.LoginView
import com.nguyendo.lift.ui.views.Auth.OnboardingView
import com.nguyendo.lift.ui.views.Auth.RegisterView
import com.nguyendo.lift.ui.nav.AuthScreens.Onboarding
import com.nguyendo.lift.ui.nav.AuthScreens.Login
import com.nguyendo.lift.ui.nav.AuthScreens.Register
import com.nguyendo.lift.ui.nav.AuthScreens.PostAuth

@Composable
fun AppStartNavGraph(
    viewModel: AuthViewModel
) {
    val navController = rememberNavController()
    val userLoggedIn = viewModel.user() != null
    val authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()
    Log.d("AppStartNavHost", "userLoggedIn: $userLoggedIn")

    NavHost(
        navController = navController,
        startDestination = if (userLoggedIn) PostAuth else Onboarding,
    ) {
        composable<AuthScreens.Onboarding> {
            OnboardingView(navController = navController)
        }
        composable<Login> {
            LoginView(viewModel = authViewModel, navController = navController)
        }
        composable<Register> {
            RegisterView(viewModel = authViewModel, navController = navController)
        }
        composable<PostAuth> {
            PostAuthNavGraph(
                logout = {
                    authViewModel.signout()
                    navController.navigate(Onboarding) {
                        popUpTo(0) {}
                    }
                },
                userId = viewModel.user()!!.uid
            )
        }
    }
}