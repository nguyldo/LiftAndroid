package com.nguyendo.lift.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nguyendo.lift.ui.viewmodel.AuthViewModel
import androidx.navigation.NavController
import com.nguyendo.lift.R
import com.nguyendo.lift.ui.nav.AuthScreens.Login
import com.nguyendo.lift.ui.nav.AuthScreens.Register

/*
* Initial onboarding view with buttons to login or register
* */
@Composable
fun OnboardingView(
    viewModel: AuthViewModel,
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title
            Text(stringResource(R.string.auth_title))

            Button(onClick = {
                navController.navigate(Login)
            }) {
                Text(stringResource(R.string.auth_login))
            }

            Button(onClick = {
                navController.navigate(Register)
            }) {
                Text(stringResource(R.string.auth_register))
            }

            Button(onClick = {
                viewModel.signout()
            }) {
                Text("Sign Out")
            }
        }
    }
}