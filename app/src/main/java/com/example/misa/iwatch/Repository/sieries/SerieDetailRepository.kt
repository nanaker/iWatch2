package com.example.misa.iwatch.Repository.sieries

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.misa.iwatch.Repository.*
import com.example.misa.iwatch.Repository.Moviess.PageKeyedMoviesDataSource
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.*
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import io.reactivex.Observable


class SerieDetailRepository (val tmdbApi: TMDBApi): IRepository {
    lateinit var serie: Series




    fun getSerieDetail(id: Int): Observable<Series> {
        println("calling serie detail repository ")
        return tmdbApi.getSerieDetailsById(id)}
    fun getSerieReview(id: Int): Observable<ReviewResponse> {
        println("calling serie detail repository review")
        return tmdbApi.getSerieReview(id)}
    fun getSerieSemilar(id: Int): Observable<SimilarSerieResponse> {
        println("calling movie detail repository similar movies ")
        return tmdbApi.getSerieSimilar(id,1)}

}



