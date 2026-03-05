package com.interrapidisimo.technical.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.interrapidisimo.technical.presentation.ui.login.LoginScreen

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = modifier
    ) {

        composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Home)
                },
            )
        }

    }
}