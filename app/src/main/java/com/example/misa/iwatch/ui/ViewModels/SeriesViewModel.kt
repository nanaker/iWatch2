package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.sieries.SeriesRepository
import com.example.misa.iwatch.entity.Film
import com.example.misa.iwatch.entity.Movie

class SeriesViewModel(val seriesRepo: SeriesRepository):ViewModel(){

    val recentFilms:MutableLiveData<List<Movie>> = MutableLiveData()

    val seriesOnTheAir = Transformations.switchMap(seriesRepo.getSeriesOnTheAir(),{
        it.pagedList
    })!!

    val networkState = Transformations.switchMap(seriesRepo.getSeriesOnTheAir(),{
        it.networkState
    })!!




}