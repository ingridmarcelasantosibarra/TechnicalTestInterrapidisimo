package com.interrapidisimo.technical.domain.model

data class AppVersion(
    val remoteVersion: String,
    val localVersion: String
) {
    fun isOutdated(): Boolean = localVersion < remoteVersion
    fun isNewer(): Boolean = localVersion > remoteVersion
    fun isUpToDate(): Boolean = localVersion == remoteVersion
}