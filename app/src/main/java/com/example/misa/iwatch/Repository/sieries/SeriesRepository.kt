package com.example.misa.iwatch.Repository.sieries

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.DataSourceKey
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Listing
import com.example.misa.iwatch.Repository.PagedListDataSourceFactory
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Series
import java.util.concurrent.Executor

class SeriesRepository(val tmdbApi: TMDBApi,
                       val networkExecutor: Executor): IRepository {

    private var seriesOnAirDataSourceFactory: PagedListDataSourceFactory<Series>
    private var popularSeriesDataSourceFactory: PagedListDataSourceFactory<Series>
    private var conf: PagedList.Config
    private var seriesOnTheAir: LiveData<PagedList<Series>>
    private var popularSeries: LiveData<PagedList<Series>>

    init {

        seriesOnAirDataSourceFactory = PagedListDataSourceFactory(tmdbApi,networkExecutor, DataSourceKey.SeriesOnAir)
        popularSeriesDataSourceFactory = PagedListDataSourceFactory(tmdbApi,networkExecutor, DataSourceKey.PopularSeries)
        conf  = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(5)
                .setPageSize(20)
                .build()

        seriesOnTheAir = LivePagedListBuilder(seriesOnAirDataSourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()

        popularSeries = LivePagedListBuilder(popularSeriesDataSourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()
    }

    @Suppress("UNCHECKED_CAST")
    fun getSeriesOnTheAir(): LiveData<Listing<Series>> {
        val listing = MutableLiveData<Listing<Series>>()
        listing.postValue(Listing(
                pagedList = seriesOnTheAir,
                networkState = Transformations.switchMap(seriesOnAirDataSourceFactory.dataSourceLiveData as MutableLiveData<SeriesOnAirPageKeyDataSource>,
                        { it.networkState})
        ))
        return listing
    }

    @Suppress("UNCHECKED_CAST")
    fun getPopularSeries(): LiveData<Listing<Series>> {
        val listing = MutableLiveData<Listing<Series>>()
        listing.postValue(Listing(
                pagedList = popularSeries,
                networkState = Transformations.switchMap(popularSeriesDataSourceFactory.dataSourceLiveData as MutableLiveData<PopularSeriesPageKeyedDataSource>,
                        { it.networkState})
        ))
        return listing
    }


}