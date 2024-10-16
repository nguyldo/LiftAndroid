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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nguyendo.lift.R

/*
* Initial onboarding view with buttons to login or register
* */
@Composable
fun OnboardingView(
    viewModel: AuthViewModel = viewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Title
            Text(stringResource(R.string.auth_title))

            if (viewModel.user() == null) {
                // Login
                Button(onClick = {
                    viewModel.login(
                        email = "nguyldo@gmail.com",
                        password = "Test123$"
                    )
                }) {
                    Text(stringResource(R.string.auth_login))
                }

                // Register
                Button(onClick = {
                    viewModel.register(
                        email = "androidtest@test.com",
                        password = "Test123$",
                        username = "androidtest",
                        name = "Android Test"
                    )
                }) {
                    Text(stringResource(R.string.auth_register))
                }
            } else {
                Button(onClick = {
                    viewModel.signout()
                }) {
                    Text("Sign Out")
                }
            }
        }
    }
}