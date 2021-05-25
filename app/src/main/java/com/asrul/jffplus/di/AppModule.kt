package com.asrul.jffplus.di

import com.asrul.jffplus.core.domain.usecase.DataInteractor
import com.asrul.jffplus.core.domain.usecase.DataUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
//    @ViewModelScoped
    abstract fun provideDataUseCase(dataInteractor: DataInteractor): DataUseCase
}