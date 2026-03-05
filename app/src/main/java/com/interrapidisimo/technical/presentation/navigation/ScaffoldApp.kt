package com.interrapidisimo.technical.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize()){ innerPadding ->
        AppNavigationHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}