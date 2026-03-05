package com.interrapidisimo.technical.data.remote.repository

import android.content.Context
import com.interrapidisimo.technical.domain.repository.AppInfoProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppInfoProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AppInfoProvider {
    override val versionName: String
        get() = context.packageManager
            .getPackageInfo(context.packageName, 0)
            .versionName.orEmpty()
}