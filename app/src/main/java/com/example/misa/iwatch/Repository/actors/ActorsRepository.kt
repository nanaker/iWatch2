package com.example.misa.iwatch.Repository.actors

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
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes
import java.util.concurrent.Executor

class ActorsRepository(val tmdbApi: TMDBApi,
                       val networkExecutor: Executor): IRepository {

    private var sourceFactory: PagedListDataSourceFactory<Personnes>
    private var conf: PagedList.Config
    private var popularActors: LiveData<PagedList<Personnes>>

    init {

        sourceFactory = PagedListDataSourceFactory<Personnes>(tmdbApi,networkExecutor, DataSourceKey.Persons)
        conf  = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(5)
                .setPageSize(20)
                .build()

        popularActors = LivePagedListBuilder(sourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()
    }

    @Suppress("UNCHECKED_CAST")
    fun getPopularActors(): LiveData<Listing<Personnes>> {
        val listing = MutableLiveData<Listing<Personnes>>()
        listing.postValue(Listing(
                pagedList = popularActors,
                networkState = Transformations.switchMap(sourceFactory.dataSourceLiveData as MutableLiveData<PageKeyedActorsDataSource>,
                        { it.networkState})
        ))
        return listing
    }

}