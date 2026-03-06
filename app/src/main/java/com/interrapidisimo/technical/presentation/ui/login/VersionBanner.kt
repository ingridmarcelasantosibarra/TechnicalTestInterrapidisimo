package com.interrapidisimo.technical.presentation.ui.login

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.interrapidisimo.technical.domain.model.AppVersion
import com.interrapidisimo.technical.presentation.viewmodel.VersionStatus

@Composable
fun VersionBanner(version: AppVersion, status: VersionStatus) {
    val (message, color) = when (status) {
        is VersionStatus.Outdated -> Pair(
            "Tu versión (${version.localVersion}) está desactualizada." +
                "Actualiza a la versión ${version.remoteVersion}.",
            Color(0xFFFF1530)
        )
        is VersionStatus.Newer -> Pair(
            "Tu versión (${version.localVersion}) es superior " +
                "a la publicada (${version.remoteVersion}).",
            Color(0xFFFF1530)
        )
        is VersionStatus.UpToDate -> Pair(
            "Versión actualizada (${version.localVersion}).",
            Color(0xFF4ECC54)
        )
    }

    Card (
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.12f)
        )
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(12.dp),
            color = color
        )
    }
}