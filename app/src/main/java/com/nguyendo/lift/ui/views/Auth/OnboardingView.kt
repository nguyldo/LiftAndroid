package com.nguyendo.lift.ui.views.Auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nguyendo.lift.R
import com.nguyendo.lift.ui.nav.AuthScreens.Login
import com.nguyendo.lift.ui.nav.AuthScreens.Register

/*
* Initial onboarding view with buttons to login or register
* */
@Composable
fun OnboardingView(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(stringResource(R.string.auth_title))

            Spacer(modifier = Modifier.height(48.dp))

            Row {
                Button(onClick = {
                    navController.navigate(Login)
                }) {
                    Text(stringResource(R.string.auth_login))
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = {
                    navController.navigate(Register)
                }) {
                    Text(stringResource(R.string.auth_register))
                }
            }
        }
    }
}