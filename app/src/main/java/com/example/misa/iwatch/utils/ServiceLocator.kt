package com.example.misa.iwatch.utils

import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.actors.ActorsRepository
import com.example.misa.iwatch.Repository.Movies.MovieRepository
import com.example.misa.iwatch.Repository.actors.ActorDetailRepository
import com.example.misa.iwatch.Repository.sieries.EpisodesDetailRepository
import com.example.misa.iwatch.Repository.sieries.SaisonDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.Repository.sieries.SeriesRepository
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.api.WebServiceFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

interface ServiceLocator{

    companion object {
        private var instance:ServiceLocator?=null
        public fun instance():ServiceLocator{
            if(instance == null) instance = DefaultServiceLocator()
            return instance!!
        }
    }

    fun getNetworkExecutor():Executor
    fun getDiskIOExecutor():Executor
    fun getApi():TMDBApi
    fun getRepository(repositoryType:IRepository.Type):IRepository
}

class DefaultServiceLocator: ServiceLocator{


    private val DISK_IO  =  Executors.newSingleThreadExecutor()
    private val NETWORK_IO =  Executors.newFixedThreadPool(4)
    private val tmdbApi by lazy {
        WebServiceFactory.create(TMDBApi::class.java)
    }

    override fun getNetworkExecutor() = NETWORK_IO

    override fun getDiskIOExecutor() = DISK_IO

    override fun getApi() = tmdbApi


    override fun getRepository(repositoryType: IRepository.Type): IRepository {
       return when(repositoryType){
           IRepository.Type.ACTORS->  ActorsRepository(
                   tmdbApi = getApi(),
                   networkExecutor = getNetworkExecutor()
           )
           IRepository.Type.MOVIE->MovieRepository(
                   tmdbApi =  getApi(),
                   networkExecutor = getNetworkExecutor(),
                   ioExecutor = getDiskIOExecutor()
           )
           IRepository.Type.SERIES->SeriesRepository(
                   tmdbApi = getApi(),
                   networkExecutor = getNetworkExecutor()
           )
           IRepository.Type.DETAILMOVIE -> MovieDetailRepository(
                   tmdbApi= getApi()
           )
           IRepository.Type.DETAILSERIE -> SerieDetailRepository(
                   tmdbApi= getApi()
           )
           IRepository.Type.DETAILPERSONNE -> ActorDetailRepository(
                   tmdbApi= getApi()
           )
           IRepository.Type.DETAILSAISON-> SaisonDetailRepository(
                   tmdbApi= getApi()
           )
           IRepository.Type.DETAILEPISODE -> EpisodesDetailRepository(
                   tmdbApi= getApi()
           )
       }
    }
}