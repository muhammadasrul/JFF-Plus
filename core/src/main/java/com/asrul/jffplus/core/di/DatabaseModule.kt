package com.asrul.jffplus.core.di

import android.content.Context
import androidx.room.Room
import com.asrul.jffplus.core.data.local.room.RoomDB
import com.asrul.jffplus.core.data.local.room.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RoomDB = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        "jff.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRoomDao(database: RoomDB): RoomDao = database.roomDao()
}