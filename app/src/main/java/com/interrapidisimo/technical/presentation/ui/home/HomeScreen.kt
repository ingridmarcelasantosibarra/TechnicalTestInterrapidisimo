package com.interrapidisimo.technical.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interrapidisimo.technical.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onTablesClick: () -> Unit,
    onLocalitiesClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Bienvenido", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        user?.let {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Usuario: ${it.usuario}")
                    Text("Identificación: ${it.identificacion}")
                    Text("Nombre: ${it.nombre}")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            onTablesClick()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Tablas")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onLocalitiesClick, modifier = Modifier.fillMaxWidth())
        {
            Text("Localidades")
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}