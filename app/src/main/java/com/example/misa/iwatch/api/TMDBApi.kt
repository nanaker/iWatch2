package com.example.misa.iwatch.api

import com.example.misa.iwatch.Repository.Listing
import com.example.misa.iwatch.entity.*

import org.w3c.dom.Comment

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import io.reactivex.Observable
import kotlin.collections.ArrayList


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


    @GET("movie/latest")
    fun getLatestMovie(): Observable<Movie>
    /**
     * Get the primary information about a movie.
     */
    @GET("movie/{movie_id}")
    fun getMovieDetailsById(@Path("movie_id") id: Int): Observable<Movie>

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
    fun getMovieUserReview(@Path("movie_id") id: Int): Observable<ReviewResponse>



    @GET("movie/{movie_id}/similar")
    fun getMovieSimilar(@Path("movie_id") id: Int,@Query("page") pageNumber: Int): Observable<SimilarMovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getcredits(@Path("movie_id") id: Int): Observable<CreditsResponse>



    @GET("person/popular")
    fun getPopularPersonne(@Query("page") pageNumber: Int): Call<ListingData<Personnes>>
    /**
     * Get the primary person details by id.
     */
    @GET("person/{person_id}")
    fun getPersonDetailsById(@Path("person_id") id: Int): Observable<Personnes>

    /**
     * Get the movie credits for a person.
     */
    @GET("person/{person_id}/movie_credits")
    fun getPersonMovieCredits(@Path("person_id") id: Int): Observable<CreditsMovieActorResponse>


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

    @GET("tv/{tv_id}")
    fun getSerieDetailsById(@Path("tv_id") id: Int): Observable<Series>

    @GET("tv/{tv_id}/reviews")
    fun getSerieReview(@Path("tv_id") id: Int): Observable<ReviewResponse>

    @GET("tv/{tv_id}/similar")
    fun getSerieSimilar(@Path("tv_id") id: Int,@Query("page") pageNumber: Int): Observable<SimilarSerieResponse>

    @GET("tv/{tv_id}/season/{season_number}")
    fun getSaisonDetail(@Path("tv_id") id: Int,@Path("season_number") nb: Int): Observable<Saisons>

    @GET("tv/{tv_id}/season/{season_number}/credits")
    fun getSaisonCredits(@Path("tv_id") id: Int,@Path("season_number") saison_number: Int): Observable<CreditsResponse>



}
