package com.example.misa.iwatch.Repository.Movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.misa.iwatch.Repository.*

import com.example.misa.iwatch.api.TMDBApi
import com.example.misa.iwatch.entity.*
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import io.reactivex.Observable


class MovieDetailRepository (val tmdbApi: TMDBApi): IRepository {
    private var mCompositeDisposable: CompositeDisposable? = null
    lateinit var movie: Movie




    fun getMovieDetail(id: Int): Observable<Movie> {
        println("calling movie detail repository ")
        return tmdbApi.getMovieDetailsById(id)}
    fun getLatestMovie(): Observable<Movie> {
        println("calling movie detail repository ")
        return tmdbApi.getLatestMovie()}
    fun getMovieReview(id: Int): Observable<ReviewResponse> {
        println("calling movie detail repository review")
        return tmdbApi.getMovieUserReview(id)
    }
    fun getMovieSimilar(id: Int): Observable<SimilarMovieResponse> {
        println("calling movie detail repository similar movies ")
        return tmdbApi.getMovieSimilar(id,1)
    }
    fun getCreditMovie(id: Int): Observable<CreditsResponse> {
        println("calling movie detail repository credits ")
        return tmdbApi.getcredits(id)}
        /*  mCompositeDisposable?.add(tmdbApi.getMovieDetailsById(id)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(this::handleResponsemovie, this::handleErrormovie))

         mCompositeDisposable?.add(tmdbApi.getcredits(id)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(this::handleResponseCredit, this::handleErrorCredit))
          mCompositeDisposable?.add(tmdbApi.getMovieSimilar(id)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(this::handleResponseSimilar, this::handleErrorSimilar))
          mCompositeDisposable?.add(tmdbApi.getMovieUserReview(id)
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe(this::handleResponseReview, this::handleErrorReview))*/



        /* .enqueue(object : Callback<Movie> {
     override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
         if ((response != null) && (response.code() == 200)) {
             println("code" + response.code() + response.message())
             simulateDelay()
             movie = response.body()!!
             movie.genres = response.body()!!.genres
           //  movie.associatefilm = getAssociateFilm(id)
          //   movie.actors = getAssociActorsFilm(id)
         //    movie.comments = getReviews(id)

         }

     }

     override fun onFailure(call: Call<Movie>, t: Throwable) {
         // Log error here since request failed
         println("error")
         Log.e("error", t.toString())
     }
 })

 return movie*/


    // private fun handleResponsemovie(movie: Movie) { this.movie = movie }
    /* private fun handleResponseCredit(actors: ArrayList<associate_Actors>) { this.movie.actors = actors }
     private fun handleResponseSimilar(associate_Movie: ArrayList<associate_Movie>) { this.movie.associatefilm= associate_Movie }
     private fun handleResponseReview(comments: ArrayList<Comments>) { this.movie.comments = comments }


     private fun handleErrorCredit(error: Throwable) { Log.d("error", error.localizedMessage) }
     private fun handleErrorSimilar(error: Throwable) { Log.d("error", error.localizedMessage) }
     private fun handleErrorReview(error: Throwable) { Log.d("error", error.localizedMessage) }*/

    //private fun handleErrormovie(error: Throwable) { Log.d("error", error.localizedMessage) }


    /*private fun getAssociateFilm(id: Int): ArrayList<associate_Movie> {
        lateinit var assmovie: ArrayList<associate_Movie>
        tmdbApi.getMovieSimilar(id).enqueue(object : Callback<ArrayList<associate_Movie>> {
            override fun onResponse(call: Call<ArrayList<associate_Movie>>, response: Response<ArrayList<associate_Movie>>) {
                if ((response != null) && (response.code() == 200)) {
                    println("code" + response.code() + response.message())
                    println("response" + response.body())
                    assmovie = response.body()?.results!!


                }

            }

            override fun onFailure(call: Call<ArrayList<associate_Movie>>, t: Throwable) {
                // Log error here since request failed
                println("error")
                Log.e("error", t.toString())
            }
        })

        return assmovie
    }

    private fun getAssociActorsFilm(id: Int): ArrayList<associate_Actors> {
        lateinit var assActors: ArrayList<associate_Actors>
        tmdbApi.getcredits(id).enqueue(object : Callback<ArrayList<associate_Actors>> {
            override fun onResponse(call: Call<ArrayList<associate_Actors>>, response: Response<ArrayList<associate_Actors>>) {
                if ((response != null) && (response.code() == 200)) {
                    println("code" + response.code() + response.message())
                    println("response" + response.body())
                    assActors = response.body()?.cast


                }

            }

            override fun onFailure(call: Call<ArrayList<associate_Actors>>, t: Throwable) {
                // Log error here since request failed
                println("error")
                Log.e("error", t.toString())
            }
        })

        return assActors
    }


    fun getReviews(id: Int): ArrayList<Comments> {
        lateinit var comments:ArrayList<Comments>
        tmdbApi.getMovieUserReview(id).enqueue(object : Callback<ArrayList<Comments>> {
            override fun onResponse(call: Call<ArrayList<Comments>>, response: Response<ArrayList<Comments>>) {
                if ((response != null) && (response.code() == 200)) {
                    println("code" + response.code() + response.message())
                    println("response"+response.body())
                    comments= response.body()?.results


                }

            }

            override fun onFailure(call: Call<ArrayList<Comments>>, t: Throwable) {
                // Log error here since request failed
                println("error")
                Log.e("error", t.toString())
            }
        })

        return comments
    }*/

}



