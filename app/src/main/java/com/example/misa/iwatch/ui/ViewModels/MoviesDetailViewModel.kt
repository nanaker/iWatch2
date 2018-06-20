package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.entity.*
import io.reactivex.Observable

class MoviesDetailViewModel(val moviesRepo:MovieDetailRepository):ViewModel(){


 fun getmovie( id:Int):Observable<Movie>{
     println("calling view model  ")
     return moviesRepo.getMovieDetail( id)
 }
    fun getreview( id:Int):Observable<ReviewResponse>{
        println("calling view model review ")
        return moviesRepo.getMovieReview( id)
    }
    fun getsimilar( id:Int):Observable<SimilarMovieResponse>{
        println("calling view model similar ")
        return moviesRepo.getMovieSimilar( id)
    }
    fun getcredits( id:Int):Observable<CreditsResponse>{
        println("calling view model credits ")
        return moviesRepo.getCreditMovie( id)
    }

}

