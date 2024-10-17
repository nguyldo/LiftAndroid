package com.nguyendo.lift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nguyendo.lift.ui.theme.LiftTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.nguyendo.lift.ui.viewmodel.AuthViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.nguyendo.lift.ui.nav.AppStartNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiftTheme {
                val authViewModel: AuthViewModel = hiltViewModel<AuthViewModel>()

                AppStartNavHost(viewModel = authViewModel)
            }
        }
    }
}

