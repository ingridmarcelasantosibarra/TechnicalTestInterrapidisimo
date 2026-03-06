package com.interrapidisimo.technical.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interrapidisimo.technical.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isLoginSuccess) {
        if (uiState.isLoginSuccess) {
            onLoginSuccess()
            viewModel.onLoginNavigated()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when {
            uiState.isVersionLoading -> CircularProgressIndicator(Modifier.size(16.dp))
            uiState.versionError != null -> Text(
                "Error versión: ${uiState.versionError}",
                color = Color.Red
            )
            uiState.version != null && uiState.versionStatus != null ->
                VersionBanner(uiState.version!!, uiState.versionStatus!!)
        }

        Spacer(Modifier.height(32.dp))
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))

        // Login error
        uiState.loginError?.let { error ->
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))) {
                Text("❌ $error", Modifier.padding(12.dp), color = Color.Red)
            }
            Spacer(Modifier.height(8.dp))
        }

        // Login button
        if (uiState.isLoginLoading) {
            CircularProgressIndicator()
        }

        Button(
            onClick = { viewModel.login() },
            enabled = !uiState.isLoginLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}