package com.example.misa.iwatch.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.actors.ActorsRepository
import com.example.misa.iwatch.Repository.sieries.SeriesRepository
import com.example.misa.iwatch.ui.ViewModels.ActorsViewModel
import com.example.misa.iwatch.ui.ViewModels.MoviesViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesViewModel


fun Fragment.getViewModel(type: IRepository.Type): ViewModel {

    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val repo = ServiceLocator.instance()
                    .getRepository(type)
            @Suppress("UNCHECKED_CAST")
            return when(type){
                IRepository.Type.MOVIE -> MoviesViewModel(repo as MovieRepository) as T
                IRepository.Type.SERIES -> SeriesViewModel(repo as SeriesRepository) as T
                IRepository.Type.ACTORS -> ActorsViewModel(repo as ActorsRepository) as T
                else -> ActorsViewModel(repo as ActorsRepository) as T
            }

        }
    })[when(type){
        IRepository.Type.MOVIE -> MoviesViewModel::class.java
        IRepository.Type.SERIES -> SeriesViewModel::class.java
        IRepository.Type.ACTORS -> ActorsViewModel::class.java
        else ->  ActorsViewModel::class.java
    }]

}