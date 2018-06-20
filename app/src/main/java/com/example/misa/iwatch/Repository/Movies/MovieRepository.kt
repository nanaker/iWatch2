package com.example.misa.iwatch.Repository.Movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.misa.iwatch.Repository.DataSourceKey
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Listing
import com.example.misa.iwatch.Repository.Moviess.MoviesInTheaterPageKeyedDataSource
import com.example.misa.iwatch.Repository.PagedListDataSourceFactory
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Movie
import java.util.concurrent.Executor

class MovieRepository (val tmdbApi: TMDBApi,
                       val networkExecutor: Executor,
                       val ioExecutor: Executor ): IRepository {


    private var moviesInTheaterDataSourceFactory: PagedListDataSourceFactory<Movie>
    private var popularMoviesDataSourceFactory: PagedListDataSourceFactory<Movie>
    private var conf:PagedList.Config
    private var moviesInTheater:LiveData<PagedList<Movie>>
    private var popularMovies:LiveData<PagedList<Movie>>

    init {

        moviesInTheaterDataSourceFactory = PagedListDataSourceFactory(tmdbApi,networkExecutor,DataSourceKey.MoviesOnTheater)
        popularMoviesDataSourceFactory = PagedListDataSourceFactory(tmdbApi,networkExecutor,DataSourceKey.PopularMovies)

        conf  = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPrefetchDistance(5)
                .setPageSize(20)
                .build()

        moviesInTheater = LivePagedListBuilder(moviesInTheaterDataSourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()

        popularMovies = LivePagedListBuilder(popularMoviesDataSourceFactory,conf)
                .setFetchExecutor(networkExecutor)
                .build()
    }

    @Suppress("UNCHECKED_CAST")
    fun getMoviesInTheater():LiveData<Listing<Movie>>{
        val listing = MutableLiveData<Listing<Movie>>()
        listing.postValue(Listing(
                pagedList = moviesInTheater,
                networkState = Transformations.switchMap(moviesInTheaterDataSourceFactory.dataSourceLiveData as MutableLiveData<MoviesInTheaterPageKeyedDataSource>,
                        { it.networkState})
        ))
        return listing
    }

    @Suppress("UNCHECKED_CAST")
    fun getPopularMovies():LiveData<Listing<Movie>>{
        val listing = MutableLiveData<Listing<Movie>>()
        listing.postValue(Listing(
                pagedList = popularMovies,
                networkState = Transformations.switchMap(popularMoviesDataSourceFactory.dataSourceLiveData as MutableLiveData<MoviesInTheaterPageKeyedDataSource>,
                        { it.networkState})
        ))
        return listing
    }




}