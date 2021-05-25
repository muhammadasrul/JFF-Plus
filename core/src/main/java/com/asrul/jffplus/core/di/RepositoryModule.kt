package com.asrul.jffplus.core.di

import com.asrul.jffplus.core.data.Repository
import com.asrul.jffplus.core.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: Repository): IRepository

}