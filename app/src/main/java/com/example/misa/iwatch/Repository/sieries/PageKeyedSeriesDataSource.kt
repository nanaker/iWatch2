package com.example.misa.iwatch.Repository.sieries

import android.arch.paging.PageKeyedDataSource
import com.example.misa.iwatch.Repository.BaseDataSource
import com.example.misa.iwatch.Repository.NetworkState
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Series
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class PageKeyedSeriesDataSource(tmdbApi: TMDBApi):BaseDataSource<Series>(tmdbApi){

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Series>) {
        networkState.postValue(NetworkState.LOADING)
        tmdbApi.getSeriesOnTheAir(1).enqueue(object : Callback<TMDBApi.ListingData<Series>> {

            override fun onResponse(call: Call<TMDBApi.ListingData<Series>>, response: Response<TMDBApi.ListingData<Series>>) {
                if(response.isSuccessful){
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()


                    callback.onResult(items,null,(pageNum+1))
                    networkState.postValue(NetworkState.LOADED)

                }else{
                    networkState.postValue(
                            NetworkState.error("error while loading : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Series>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
                loadInitial(params,callback)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Series>) {
        networkState.postValue(NetworkState.LOADING)
        tmdbApi.getSeriesOnTheAir(params.key).enqueue(object : Callback<TMDBApi.ListingData<Series>> {

            override fun onResponse(call: Call<TMDBApi.ListingData<Series>>, response: Response<TMDBApi.ListingData<Series>>) {
                if(response.isSuccessful){
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()


                    callback.onResult(items,pageNum+1)
                    networkState.postValue(NetworkState.LOADED)

                }else{
                    networkState.postValue(
                            NetworkState.error("error while loading : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Series>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
               loadAfter(params,callback)
            }
        })
    }


}