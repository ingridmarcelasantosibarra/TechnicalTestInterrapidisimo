package com.interrapidisimo.technical.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VersionDto(
    @SerializedName("VPStoreAppControl") val version: String?
)
