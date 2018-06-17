package com.example.misa.iwatch.Repository

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList


data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>
)