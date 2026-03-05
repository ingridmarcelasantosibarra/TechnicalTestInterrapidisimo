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
import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val versionState by viewModel.versionState.collectAsStateWithLifecycle()
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LaunchedEffect(loginState) {
        if (loginState is Resource.Success) onLoginSuccess()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when (val v = versionState) {
            is Resource.Success -> {
                val version = v.data
                val (msg, color) = when {
                    version.isOutdated() -> "⚠️ Tu versión (${version.localVersion}) está desactualizada. Última: ${version.remoteVersion}" to Color(
                        0xFFF57C00
                    )

                    version.isNewer() -> "ℹ️ Versión local (${version.localVersion}) más nueva que la remota." to Color(
                        0xFF1976D2
                    )

                    else -> "✅ App actualizada (${version.localVersion}) " to Color(0xFF388E3C)
                }
                Text(msg, color = color, style = MaterialTheme.typography.bodyMedium)
            }

            is Resource.Error -> Text("Error versión: ${v.message} ", color = Color.Red)
            else -> CircularProgressIndicator(modifier = Modifier.size(16.dp))
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text("Iniciar Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login() },
            enabled = loginState !is Resource.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }
}