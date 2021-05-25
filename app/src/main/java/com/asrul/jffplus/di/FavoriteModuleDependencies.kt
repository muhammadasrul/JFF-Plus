package com.asrul.jffplus.di

import com.asrul.jffplus.core.domain.usecase.DataUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavoriteModuleDependencies {

    fun dataUseCase(): DataUseCase
}