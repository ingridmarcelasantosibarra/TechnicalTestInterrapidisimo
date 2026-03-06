package com.interrapidisimo.technical.core.di

import com.interrapidisimo.technical.core.network.NetworkManager
import com.interrapidisimo.technical.data.remote.api.DataApi
import com.interrapidisimo.technical.data.remote.api.SecurityApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideSecurityApi(): SecurityApi =
        NetworkManager.retrofit.create(SecurityApi::class.java)
    @Provides
    @Singleton
    fun provideDataApi(): DataApi =
        NetworkManager.retrofit.create(DataApi::class.java)
}