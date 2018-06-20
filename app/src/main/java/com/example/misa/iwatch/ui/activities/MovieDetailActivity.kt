package com.example.misa.iwatch.ui.activities

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.ui.fragments.CommentsFragment
import com.example.misa.iwatch.ui.fragments.DetailsFragment
import com.example.misa.iwatch.ui.fragments.RoomsFragment
import android.view.View
import com.example.misa.iwatch.entity.Movie
import android.view.MenuItem
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import kotlin.collections.ArrayList
import com.example.misa.iwatch.entity.data
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import kotlinx.android.synthetic.main.activity_movie_detail.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieDetailActivity : AppCompatActivity() {

   lateinit var film: Movie
     var id: Int = 0

   lateinit var fragmentDetail:DetailsFragment
    lateinit var fragmentRoom:RoomsFragment
    lateinit var fragmentComments:CommentsFragment


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = this.intent
        val bundle = intent.extras


        id=bundle!!.getInt("id_movie")
        println("movie id activity "+id)

        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository

        val DetailFilmModel =  MoviesDetailViewModel(repo)
        DetailFilmModel.getmovie(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)

      movieFavori.setOnClickListener(){
          println("add fav film ")
          movieFavori.isFavorite=true
          film.fav = true
          film.comments=fragmentComments.comments
          println("comments size "+film.comments.size+" "+film.comments)
          film.associatefilm=fragmentDetail.associate_film
          println("associate film  size "+film.associatefilm.size+" "+film.associatefilm)
          film.actors=fragmentDetail.associate_Actors
          film.room=data.getCinema()
          data.Filmsfav.add(film)

      }


    }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        val intent = this.intent

        val bundle = intent.extras
        when(id){

            android.R.id.home -> super.onBackPressed()

        }
        return true
    }





    fun addfav_film() {
        // set the function to add a favoris film
        println("add fav film ")
        film.fav = true
       film.comments=fragmentComments.comments
        println("comments size "+film.comments.size+" "+film.comments)
        film.associatefilm=fragmentDetail.associate_film
        println("associate film  size "+film.associatefilm.size+" "+film.associatefilm)
        film.actors=fragmentDetail.associate_Actors
        film.room=data.getCinema()
        data.Filmsfav.add(film)



    }
    private fun handleResponse(movie: Movie) {
       this.film = movie
    println("film result title "+film.title)
        title_movie_detail.text= film!!.title
        details_movie.text= film!!.genres[0].name +" , "+film!!.genres[1].name
        directorName_detail.text= film!!.release_date
       rating_movie.rating = film!!.voteAverage
               storyLine.text= film!!.info
       Glide.with(this)
               .load(film!!.image)
               .into(pictureMovieDetail)

       film.fav=false
        if(film!!.fav) movieFavori.isFavorite = true

       /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_anonce.setMediaController(mediaController)

        try {
            bande_anonce.setVideoURI(Uri.parse( "android.resource://" + getPackageName()+ "/"+ R.raw.video_harry ))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

       //addfav_film()

       setSupportActionBar(findViewById(R.id.my_toolbar))
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
       supportActionBar?.title = film.title

       rateResult.visibility = View.GONE



       setTitle("Details")

       val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)
        fragmentDetail=DetailsFragment.newInstance(film!!.id)
         fragmentRoom=RoomsFragment.newInstance();
         fragmentComments=CommentsFragment.newInstance(film!!.id)
       pageAdapter.addFragment(fragmentDetail, "DETAILS")
       pageAdapter.addFragment(fragmentRoom, "ROOMS")
       pageAdapter.addFragment(fragmentComments, "COMMENTS")

       println("page container")
       println("data cinema size "+data.getCinema().size)
       movieContainer.adapter = pageAdapter
       Movietabs.setupWithViewPager(movieContainer)

    }
    private fun handleError(error: Throwable) { Log.d("error movie load ", error.localizedMessage) }





}
