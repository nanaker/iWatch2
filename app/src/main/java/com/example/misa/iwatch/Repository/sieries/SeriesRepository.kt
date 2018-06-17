package com.example.misa.iwatch.Repository.sieries

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
import com.example.misa.iwatch.entity.Series
import java.util.concurrent.Executor

class SeriesRepository(val tmdbApi: TMDBApi,
                       val networkExecutor: Executor): IRepository {

    private var sourceFactory: PagedListDataSourceFactory<Series>
    private var conf: PagedList.Config
    private var SeriesOnTheAir: LiveData<PagedList<Series>>

    init {

        sourceFactory = PagedListDataSourceFactory<Series>(tmdbApi,networkExecutor, DataSourceKey.Series)
        conf  = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(5)
                .setPageSize(20)
                .build()

        SeriesOnTheAir = LivePagedListBuilder(sourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()
    }

    @Suppress("UNCHECKED_CAST")
    fun getSeriesOnTheAir(): LiveData<Listing<Series>> {
        val listing = MutableLiveData<Listing<Series>>()
        listing.postValue(Listing(
                pagedList = SeriesOnTheAir,
                networkState = Transformations.switchMap(sourceFactory.dataSourceLiveData as MutableLiveData<PageKeyedMoviesDataSource>,
                        { it.networkState})
        ))
        return listing
    }


}