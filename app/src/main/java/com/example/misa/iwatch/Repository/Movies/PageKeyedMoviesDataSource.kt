package com.example.misa.iwatch.Repository.Moviess

import android.util.Log
import com.example.misa.iwatch.Repository.BaseDataSource
import com.example.misa.iwatch.Repository.NetworkState
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageKeyedMoviesDataSource(tmdbApi: TMDBApi):BaseDataSource<Movie>(tmdbApi){

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        tmdbApi.getMoviesInTheater(1).enqueue(object : Callback<TMDBApi.ListingData<Movie>>{

            override fun onResponse(call: Call<TMDBApi.ListingData<Movie>>, response: Response<TMDBApi.ListingData<Movie>>) {
                if(response.isSuccessful){
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()


                    callback.onResult(items,null,pageNum+1)
                    networkState.postValue(NetworkState.LOADED)

                }else{
                    networkState.postValue(
                            NetworkState.error("error while loading : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Movie>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
                loadInitial(params,callback)
            }
        })
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        networkState.postValue (NetworkState.LOADING)
        tmdbApi.getMoviesInTheater(params.key).enqueue(object : Callback<TMDBApi.ListingData<Movie>>{

            override fun onResponse(call: Call<TMDBApi.ListingData<Movie>>, response: Response<TMDBApi.ListingData<Movie>>) {
               if(response.isSuccessful){
                   val items    = response.body()?.results!!
                   val pageNum :Int = response.body()?.page!!.toInt()

                   callback.onResult(items,(pageNum+1))
                   networkState.postValue(NetworkState.LOADED)

               }else{
                   networkState.postValue(
                           NetworkState.error("error while loading : ${response.code()}"))
               }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Movie>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
                loadAfter(params,callback)
            }
        })
    }


}