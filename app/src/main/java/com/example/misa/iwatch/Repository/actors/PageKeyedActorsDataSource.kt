package com.example.misa.iwatch.Repository.actors

import android.arch.paging.PageKeyedDataSource
import com.example.misa.iwatch.Repository.BaseDataSource
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.Personnes
import java.util.concurrent.Executor

class PageKeyedActorsDataSource(tmdbApi: TMDBApi):BaseDataSource<Personnes>(tmdbApi){


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Personnes>) {

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Personnes>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}