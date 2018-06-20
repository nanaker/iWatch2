package com.example.misa.iwatch.Repository.actors

import com.example.misa.iwatch.Repository.BaseDataSource
import com.example.misa.iwatch.Repository.NetworkState
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageKeyedActorsDataSource(tmdbApi: TMDBApi):BaseDataSource<Personnes>(tmdbApi){


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Personnes>) {
        networkState.postValue (NetworkState.LOADING)
        tmdbApi.getPopularPersonne(params.key).enqueue(object : Callback<TMDBApi.ListingData<Personnes>> {

            override fun onResponse(call: Call<TMDBApi.ListingData<Personnes>>, response: Response<TMDBApi.ListingData<Personnes>>) {
                if(response.isSuccessful){
                    lateinit var detailedList:MutableList<Personnes>
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()

                    items.forEach {
                        tmdbApi.getPersonDetailsById(it.id).enqueue( object :Callback<Personnes>{
                            override fun onFailure(call: Call<Personnes>?, t: Throwable?) {
                                networkState.postValue(
                                        NetworkState.error("error  : ${t?.message}")
                                )
                                loadAfter(params,callback)
                            }

                            override fun onResponse(call: Call<Personnes>?, response: Response<Personnes>?) {
                                detailedList.add(response?.body()!!)
                            }
                        })

                    }
                    callback.onResult(items,(pageNum+1))
                    networkState.postValue(NetworkState.LOADED)

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

            override fun onResponse(call: Call<TMDBApi.ListingData<Personnes>>, response: Response<TMDBApi.ListingData<Personnes>>) {
                if(response.isSuccessful){
                     var detailedList:MutableList<Personnes> = mutableListOf<Personnes>()
                    val items    = response.body()?.results!!
                    val pageNum :Int = response.body()?.page!!.toInt()
                    items.forEach {
                        val call = tmdbApi.getPersonDetailsById(it.id)
                        val res  = call.execute()
                        if (res.isSuccessful)  detailedList.add(res?.body()!!)

                    }
                    callback.onResult(detailedList,null,pageNum+1)
                    networkState.postValue(NetworkState.LOADED)

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