package com.example.misa.iwatch.Repository.sieries

import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.CreditsResponse
import com.example.misa.iwatch.entity.Saisons
import com.example.misa.iwatch.entity.Series
import io.reactivex.Observable


class SaisonDetailRepository (val tmdbApi: TMDBApi): IRepository {
    lateinit var serie: Series




    fun getSaisonDetail(id: Int,nb:Int): Observable<Saisons> {
        println("calling serie detail repository ")
        return tmdbApi.getSaisonDetail(id,nb)}
    fun getSerieCredits(id: Int,nb:Int): Observable<CreditsResponse> {
        println("calling serie detail repository review")
        return tmdbApi.getSaisonCredits(id,nb)}

}



