package com.interrapidisimo.technical.core.di

import com.interrapidisimo.technical.data.remote.repository.SecurityRepository
import com.interrapidisimo.technical.domain.repository.SecurityRepositoryImpl
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