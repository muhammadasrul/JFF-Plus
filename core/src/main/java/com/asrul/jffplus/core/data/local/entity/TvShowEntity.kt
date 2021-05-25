package com.asrul.jffplus.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asrul.jffplus.core.utils.Maps
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = Maps.tvShowTable)
data class TvShowEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Maps.id)
    var id: String,

    @NotNull
    @ColumnInfo(name = Maps.title)
    var title: String,

    @NonNull
    @ColumnInfo(name = Maps.release)
    var releaseDate: String,

    @NonNull
    @ColumnInfo(name = Maps.popularity)
    var popularity: String,

    @NonNull
    @ColumnInfo(name = Maps.voteCount)
    var voteCount: String,

    @NonNull
    @ColumnInfo(name = Maps.posterPath)
    var posterPath: String,

    @NonNull
    @ColumnInfo(name = Maps.backdropPath)
    var backdropPath: String,

    @NonNull
    @ColumnInfo(name = Maps.overview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = Maps.voteAverage)
    var voteAverage: Double,

    @NonNull
    @ColumnInfo(name = Maps.favorite)
    var favorite: Boolean = false
) : Parcelable
