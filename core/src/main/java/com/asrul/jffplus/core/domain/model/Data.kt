package com.asrul.jffplus.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    var id: String,
    var title: String,
    var releaseDate: String,
    var popularity: String,
    var voteCount: String,
    var posterPath: String,
    var backdropPath: String,
    var overview: String,
    var voteAverage: Double,
    var favorite: Boolean = false
) : Parcelable
