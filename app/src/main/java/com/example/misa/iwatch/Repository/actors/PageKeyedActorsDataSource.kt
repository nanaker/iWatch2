package com.example.misa.iwatch.Repository.actors

import android.annotation.SuppressLint
import android.util.Log
import com.example.misa.iwatch.Repository.BaseDataSource
import com.example.misa.iwatch.Repository.NetworkState
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.utils.DefaultServiceLocator
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.androidannotations.annotations.UiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageKeyedActorsDataSource(tmdbApi: TMDBApi):BaseDataSource<Personnes>(tmdbApi){


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Personnes>) {
        networkState.postValue (NetworkState.LOADING)
        tmdbApi.getPopularPersonne(params.key).enqueue(object : Callback<TMDBApi.ListingData<Personnes>> {

            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<TMDBApi.ListingData<Personnes>>, response: Response<TMDBApi.ListingData<Personnes>>) {
                if(response.isSuccessful){
                    lateinit var detailedList:MutableList<Personnes>
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()

                    Observable.fromIterable(items)
                            .flatMap { tmdbApi.getPersonDetailsById(it.id) }
                            .toList()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.from(ServiceLocator.instance().getNetworkExecutor()))
                            .subscribe({
                                callback.onResult(it,(pageNum+1))
                                networkState.postValue(NetworkState.LOADED)
                            }, {
                                networkState.postValue(
                                        NetworkState.error("error  : ${it.message}")
                                )
                                loadAfter(params,callback)
                            })
                }else{
                    networkState.postValue(
                            NetworkState.error("error while loading : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Personnes>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
                loadAfter(params,callback)
            }
        })
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Personnes>) {
        networkState.postValue(NetworkState.LOADING)
        tmdbApi.getPopularPersonne(1).enqueue(object : Callback<TMDBApi.ListingData<Personnes>>{

            @SuppressLint("CheckResult")
            override fun onResponse(call: Call<TMDBApi.ListingData<Personnes>>, response: Response<TMDBApi.ListingData<Personnes>>) {
                if(response.isSuccessful){
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()

                        Observable.fromIterable(items)
                                .flatMap { tmdbApi.getPersonDetailsById(it.id) }
                                .toList()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.from(ServiceLocator.instance().getNetworkExecutor()))
                                .subscribe({
                                    callback.onResult(it,null,(pageNum+1))
                                    networkState.postValue(NetworkState.LOADED)
                                }, {
                                    networkState.postValue(
                                            NetworkState.error("error  : ${it.message}")
                                    )
                                    loadInitial(params,callback)
                                })

                }else{
                    networkState.postValue(
                            NetworkState.error("error while loading : ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<TMDBApi.ListingData<Personnes>>, t: Throwable) {
                networkState.postValue(
                        NetworkState.error("error  : ${t.message}")
                )
                loadInitial(params,callback)
            }
        })
    }
}