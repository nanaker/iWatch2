package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.misa.iwatch.Repository.actors.ActorsRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes

class ActorsViewModel(actorsRepo: ActorsRepository):ViewModel(){
    val actorsViewModel:MutableLiveData<List<Movie>> = MutableLiveData()

    val filmsOnTheater = Transformations.switchMap(actorsRepo.getPopularActors(),{
        it.pagedList
    })!!

    val networkState = Transformations.switchMap(actorsRepo.getPopularActors(),{
        it.networkState
    })!!



}