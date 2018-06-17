package com.example.misa.iwatch.Repository.actors

import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.api.WebServiceFactory
import java.util.concurrent.Executor

class ActorsRepository(val webServise: TMDBApi,
                       val networkExecutor: Executor): IRepository {


}