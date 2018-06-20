package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.*
import io.reactivex.Observable

class SeriesDetailViewModel(val serieRepo:SerieDetailRepository):ViewModel(){


 fun getserie( id:Int):Observable<Series>{
     println("calling view model  ")
     return serieRepo.getSerieDetail( id)
 }
    fun getreview( id:Int):Observable<ReviewResponse>{
        println("calling view model review ")
        return serieRepo.getSerieReview( id)
    }
    fun getsimilar( id:Int):Observable<SimilarSerieResponse>{
        println("calling view model similar ")
        return serieRepo.getSerieSemilar( id)
    }
    

}

