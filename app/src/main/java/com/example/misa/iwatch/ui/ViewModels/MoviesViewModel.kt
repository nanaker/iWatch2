package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.entity.Film
import com.example.misa.iwatch.entity.Movie

class MoviesViewModel(val moviesRepo:MovieRepository):ViewModel(){

    val filmsOnTheater = Transformations.switchMap(moviesRepo.getMoviesInTheater(),{
        it.pagedList
    })!!

    val popularFilms = Transformations.switchMap(moviesRepo.getPopularMovies(),{
        it.pagedList
    })!!

    val filmsOnTheaterNetworkState = Transformations.switchMap(moviesRepo.getMoviesInTheater(),{
        it.networkState
    })!!

    val popularFilmsNetworkState = Transformations.switchMap(moviesRepo.getPopularMovies(),{
        it.networkState
    })!!



}