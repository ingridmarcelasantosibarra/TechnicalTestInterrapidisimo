package com.interrapidisimo.technical.domain.usecase

import com.interrapidisimo.technical.core.utils.Resource
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import com.interrapidisimo.technical.domain.model.AppVersion
import com.interrapidisimo.technical.domain.repository.AppInfoProvider
import jakarta.inject.Inject

class CheckVersionUseCase @Inject constructor(
    private val repository: SecurityRepository,
    private val appInfoProvider: AppInfoProvider
) {
    /** Returns an AppVersion with both remote and local versions for comparison */
    suspend operator fun invoke(): Resource<AppVersion> {
        return when (val result = repository.getRemoteVersion()) {
            is Resource.Success -> Resource.Success(
                AppVersion(
                    remoteVersion = result.data,
                    localVersion = appInfoProvider.versionName.replace(".", "")
                )
            )

            is Resource.Error -> result
            is Resource.Loading -> result
        }
    }
}