package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.actors.ActorDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.*
import io.reactivex.Observable

class ActorDetailViewModel(val actorRepo:ActorDetailRepository):ViewModel(){


 fun getactor( id:Int):Observable<Personnes>{
     println("calling view model actor  ")
     return actorRepo.getActorDetail(id)
 }
    fun getcreditsmovies( id:Int):Observable<CreditsMovieActorResponse>{
        println("calling view model credit ")
        return actorRepo.getActorCredit(id)
    }
    

}

