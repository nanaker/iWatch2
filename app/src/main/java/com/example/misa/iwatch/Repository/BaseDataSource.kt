package com.example.misa.iwatch.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Personnes

abstract class BaseDataSource<T>(val tmdbApi: TMDBApi): PageKeyedDataSource<Int, T>(){

    val networkState = MutableLiveData<NetworkState>()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }
}