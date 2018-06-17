package com.example.misa.iwatch.Repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

data class NetworkState private constructor(
        val status: Status,
        val message:String? = null){
    companion object {
        val LOADING = NetworkState(Status.RUNNING)
        val LOADED = NetworkState(Status.SUCCESS)
        fun error(message: String?)  = NetworkState(Status.FAILED,message)
    }
}
