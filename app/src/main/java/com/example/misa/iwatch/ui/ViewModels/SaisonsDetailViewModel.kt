package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.*
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.sieries.SaisonDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.*
import io.reactivex.Observable

class SaisonsDetailViewModel(val saisonRepo: SaisonDetailRepository):ViewModel(){


 fun getsaison( id:Int,nb:Int):Observable<Saisons>{
     println("calling view model  ")
     return saisonRepo.getSaisonDetail( id,nb)
 }
    fun getcredits( id:Int,nb:Int):Observable<CreditsResponse>{
        println("calling view model review ")
        return saisonRepo.getSerieCredits( id,nb)
    }

    

}

