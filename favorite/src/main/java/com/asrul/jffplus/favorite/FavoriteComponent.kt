package com.asrul.jffplus.favorite

import android.content.Context
import com.asrul.jffplus.di.FavoriteModuleDependencies
import com.asrul.jffplus.favorite.ui.movie.FavMovieFragment
import com.asrul.jffplus.favorite.ui.tvshow.FavTvShowFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(fragment: FavMovieFragment)
    fun inject(fragment: FavTvShowFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}