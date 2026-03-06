package com.interrapidisimo.technical.core.di

import com.interrapidisimo.technical.data.remote.repository.AppInfoProviderImpl
import com.interrapidisimo.technical.data.remote.repository.DataRepositoryImpl
import com.interrapidisimo.technical.data.remote.repository.HomeLocalRepositoryImpl
import com.interrapidisimo.technical.data.remote.repository.LocalitiesRepositoryImpl
import com.interrapidisimo.technical.domain.repository.SecurityRepository
import com.interrapidisimo.technical.data.remote.repository.SecurityRepositoryImpl
import com.interrapidisimo.technical.domain.repository.AppInfoProvider
import com.interrapidisimo.technical.domain.repository.DataRepository
import com.interrapidisimo.technical.domain.repository.HomeLocalRepository
import com.interrapidisimo.technical.domain.repository.LocalitiesRepository
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

    @Binds
    @Singleton
    abstract fun homeLocalRepository(impl: HomeLocalRepositoryImpl): HomeLocalRepository

    @Binds
    @Singleton
    abstract fun dataRepository(impl: DataRepositoryImpl): DataRepository

    @Binds
    @Singleton
    abstract fun localitiesRepository(impl: LocalitiesRepositoryImpl): LocalitiesRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAppInfoProvider(
        impl: AppInfoProviderImpl
    ): AppInfoProvider
}