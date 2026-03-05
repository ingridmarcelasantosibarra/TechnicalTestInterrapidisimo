package com.interrapidisimo.technical.core.di

import com.interrapidisimo.technical.data.remote.repository.AppInfoProviderImpl
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import com.interrapidisimo.technical.data.remote.repository.SecurityRepositoryImpl
import com.interrapidisimo.technical.domain.repository.AppInfoProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSecurityRepo(impl: SecurityRepositoryImpl): SecurityRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAppInfoProvider(
        impl: AppInfoProviderImpl
    ): AppInfoProvider
}