package com.example.misa.iwatch.Repository.Movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.DataSourceKey
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Listing
import com.example.misa.iwatch.Repository.Moviess.PageKeyedMoviesDataSource
import com.example.misa.iwatch.Repository.PagedListDataSourceFactory
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Film
import com.example.misa.iwatch.entity.Movie
import java.util.concurrent.Executor

class MovieRepository (val tmdbApi: TMDBApi,
                       val networkExecutor: Executor,
                       val ioExecutor: Executor ): IRepository {

    private var sourceFactory: PagedListDataSourceFactory<Movie>
    private var conf:PagedList.Config
    private var MoviesInTheater:LiveData<PagedList<Movie>>

    init {

        sourceFactory = PagedListDataSourceFactory<Movie>(tmdbApi,networkExecutor,DataSourceKey.Movie)
        conf  = PagedList.Config.Builder()
                                 .setEnablePlaceholders(true)
                                 .setInitialLoadSizeHint(20)
                                 .setPrefetchDistance(5)
                                 .setPageSize(20)
                                 .build()

        MoviesInTheater = LivePagedListBuilder(sourceFactory,conf)
                                     .setFetchExecutor(networkExecutor)
                                     .build()
    }

    @Suppress("UNCHECKED_CAST")
    fun getMoviesInTheater():LiveData<Listing<Movie>>{
        val listing = MutableLiveData<Listing<Movie>>()
            listing.postValue(Listing(
                    pagedList = MoviesInTheater,
                    networkState = Transformations.switchMap(sourceFactory.dataSourceLiveData as MutableLiveData<PageKeyedMoviesDataSource>,
                            { it.networkState})
            ))
        return listing
    }



}