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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nguyendo.lift.R
import com.nguyendo.lift.ui.nav.AuthScreens
import com.nguyendo.lift.ui.viewmodel.AuthViewModel

@Composable
fun RegisterView(
    viewModel: AuthViewModel,
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                viewModel.register(
                    email = "androidtest@test.com",
                    password = "Test123$",
                    username = "androidtest",
                    name = "Android Test"
                ).also {
                    navController.navigate(AuthScreens.PostAuth) {
                        popUpTo(0) {}
                    }
                }
            }) {
                Text(stringResource(R.string.auth_register))
            }
        }
    }
}