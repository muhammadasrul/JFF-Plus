package com.asrul.jffplus.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asrul.jffplus.core.data.local.entity.MovieEntity
import com.asrul.jffplus.core.data.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}