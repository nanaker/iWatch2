package com.example.misa.iwatch.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class WebServiceFactory {

    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        val TAG_BDD = "BDD"
        val TAG_API = "API"
        val API_DEFAULT_PAGE_KEY = 1

        private val builder = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        private lateinit var retrofit:Retrofit

        private val logging = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        private val httpClient = OkHttpClient.Builder()

        private val apiKeyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()

            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", "94327dc22a17d2c12b806d241682cd96")
                    .build()
            val request = originalRequest.newBuilder().url(url).build()
            chain.proceed(request)
        }

        fun <S> create(serviceClass: Class<S>): S {
            if (!httpClient.interceptors().contains(logging)) {
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            if (!httpClient.interceptors().contains(apiKeyInterceptor)) {
                httpClient.addInterceptor(apiKeyInterceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
            return retrofit.create(serviceClass)
        }


    }







}