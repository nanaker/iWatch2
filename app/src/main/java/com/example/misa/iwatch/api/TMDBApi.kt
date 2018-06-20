package com.example.misa.iwatch.api

import com.example.misa.iwatch.Repository.Listing
import com.example.misa.iwatch.entity.Film
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.entity.Series

import org.w3c.dom.Comment

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBApi {


    /**
     * Get the most newly created movie.
     */
    @get:GET("movie/latest")
    val latestMovies: Call<ListingData<Movie>>


    /**
     * Get a list of movies in theatres.
     */
    @GET("movie/now_playing")
    fun getMoviesInTheater(@Query("page") pageNumber: Int): Call<ListingData<Movie>>

    /**
     * Get a list of popular movies .
     */
    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageNumber: Int): Call<ListingData<Movie>>



    /**
     * Get the primary information about a movie.
     */
    @GET("movie/{movie_id}")
    fun getMovieDetailsById(@Path("movie_id") id: Int): Call<Film>

    /**
     * Get the cast and crew for a movie.
     */
    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") id: Int): Call<Personnes>

    /**
     * Get the videos that have been added to a movie.
     */
    @GET("movie/{movie_id}/videos")
    fun getMovieVideo(@Path("movie_id") id: Int): Call<ResponseBody>


    /**
     * Get the user reviews for a movie.
     */
    @GET("movie/{movie_id}/reviews")
    fun getMovieUserReview(@Path("movie_id") id: Int): Call<Comment>



    @GET("person/popular")
    fun getPopularPersonne(@Query("page") pageNumber: Int): Call<ListingData<Personnes>>
    /**
     * Get the primary person details by id.
     */
    @GET("person/{person_id}")
    fun getPersonDetailsById(@Path("person_id") id: Int): Call<Personnes>

    /**
     * Get the movie credits for a person.
     */
    @GET("person/{person_id}/movie_credits")
    fun getPersonMovieCredits(@Path("person_id") id: Int): Call<Film>


    /**
     * Get a list of movies in theatres.
     */
    @GET("tv/on_the_air")
    fun getSeriesOnTheAir(@Query("page") pageNumber: Int): Call<ListingData<Series>>

    @GET("tv/popular")
    fun getPopularSeries(@Query("page") pageNumber: Int): Call<ListingData<Series>>

    class ListingData<T>(
            val results:List<T>,
            val page:String
    )

}
