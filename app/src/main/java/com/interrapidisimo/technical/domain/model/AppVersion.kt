package com.interrapidisimo.technical.domain.model

data class AppVersion(
    val remoteVersion: String,
    val localVersion: String
) {
    private fun safeToInt(value: String): Int =
        value.filter { it.isDigit() }.toIntOrNull() ?: 0

    fun isOutdated(): Boolean = safeToInt(localVersion) < safeToInt(remoteVersion)
    fun isNewer(): Boolean = safeToInt(localVersion) > safeToInt(remoteVersion)
}