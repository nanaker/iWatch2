package com.example.misa.iwatch.Repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.example.misa.iwatch.Repository.Movies.PopularMoviesPageKeyedDataSource
import com.example.misa.iwatch.Repository.Moviess.MoviesInTheaterPageKeyedDataSource
import com.example.misa.iwatch.Repository.actors.PageKeyedActorsDataSource
import com.example.misa.iwatch.Repository.sieries.PopularSeriesPageKeyedDataSource
import com.example.misa.iwatch.Repository.sieries.SeriesOnAirPageKeyDataSource
import com.example.misa.iwatch.api.TMDBApi
import java.util.concurrent.Executor

/**
 * a data source factory to return the latest loaded data source
 * @param T: a generic type to express an actor, a film or a series class
 */

enum class DataSourceKey private constructor(s: String) {
    Persons("Persons"),
    MoviesOnTheater("MoviesOnTheater"),
    PopularMovies("PopularMovies"),
    SeriesOnAir("SeriesOnAir"),
    PopularSeries("PopularSeries")




}
class PagedListDataSourceFactory<T:Any>(val tmdbApi:TMDBApi,val networkExecutor: Executor,val dataSourceKey:DataSourceKey):DataSource.Factory<Int,T>(){

    val dataSourceLiveData = MutableLiveData< PageKeyedDataSource<Int,T> >()

    override fun create(): DataSource<Int, T> {
        lateinit var source:PageKeyedDataSource<Int,T>
        when(dataSourceKey){
            DataSourceKey.Persons-> source = PageKeyedActorsDataSource(tmdbApi) as PageKeyedDataSource<Int, T>
            DataSourceKey.MoviesOnTheater -> source = MoviesInTheaterPageKeyedDataSource(tmdbApi) as PageKeyedDataSource<Int, T>
            DataSourceKey.PopularMovies -> source = PopularMoviesPageKeyedDataSource(tmdbApi) as PageKeyedDataSource<Int, T>
            DataSourceKey.SeriesOnAir -> source = SeriesOnAirPageKeyDataSource(tmdbApi) as PageKeyedDataSource<Int, T>
            DataSourceKey.PopularSeries -> source = PopularSeriesPageKeyedDataSource(tmdbApi) as PageKeyedDataSource<Int, T>


        }

        dataSourceLiveData.postValue(source)
        return source
        }

}

