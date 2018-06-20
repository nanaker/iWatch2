package com.example.misa.iwatch.ui.activities

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
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
import com.example.misa.iwatch.entity.data
import com.example.misa.iwatch.room.filmdb.filmDataBase
import com.example.misa.iwatch.room.filmdb.modal.film
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import kotlinx.android.synthetic.main.activity_movie_detail.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


class MovieDetailActivity : AppCompatActivity() {

    lateinit var film: Movie
    var id: Int = 0

    lateinit var fragmentDetail:DetailsFragment
    lateinit var fragmentRoom:RoomsFragment
    lateinit var fragmentComments:CommentsFragment

    // variables for room data base
    private var mDb: filmDataBase ? = null
    private var filmFav: film? = null


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

        mDb = filmDataBase.getInstance(this)

      movieFavori.setOnClickListener(){
          filmFav = film(film.id, film.title, film.info, film.release_date,
                  "", film.voteAverage, "")
          println("add fav film ")
          movieFavori.isFavorite = true
          film.fav = true
          film.comments = fragmentComments.comments
          println("comments size "+film.comments.size+" "+film.comments)
          film.associatefilm = fragmentDetail.associate_film
          println("associate film  size "+film.associatefilm.size+" "+film.associatefilm)
          film.actors = fragmentDetail.associate_Actors
          film.room = data.getCinema()
          data.Filmsfav.add(film)

      }

    }

    private fun setResult(film: film, flag: Int) {
        setResult(flag, Intent().putExtra("filmfav", film.id))
        finish()
    }

    private class InsertTask// only retain a weak reference to the activity
    internal constructor(context: MovieDetailActivity, private val film: film) : AsyncTask<Void, Void, Boolean>() {

        private val activityReference: WeakReference<MovieDetailActivity>

        init {
            activityReference = WeakReference<MovieDetailActivity>(context)
        }

        // doInBackground methods runs on a worker thread
        override fun doInBackground(vararg objs: Void): Boolean? {
            // retrieve auto incremented note id
            val j = activityReference.get()!!.mDb!!.getFilmDao().addFilmFav(film)
            Log.e("ID ", "doInBackground: $j")
            return true
        }

        // onPostExecute runs on main thread
        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                activityReference.get()!!.setResult(film, 1)
                activityReference.get()!!.finish()
            }
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
        film.comments = fragmentComments.comments
        println("comments size " + film.comments.size + " " + film.comments)
        film.associatefilm = fragmentDetail.associate_film
        println("associate film  size " + film.associatefilm.size + " " + film.associatefilm)
        film.actors = fragmentDetail.associate_Actors
        film.room = data.getCinema()
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
        if (film.image!=null){
       Glide.with(this)
               .load(film!!.image)
               .into(pictureMovieDetail)}
        rate.text= film.voteAverage.toString()
        rating_movie.rating=film.voteAverage

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





       setTitle("Details")

       val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)
        fragmentDetail=DetailsFragment.newInstance(film!!.id)
         fragmentRoom=RoomsFragment.newInstance();
         fragmentComments=CommentsFragment.newInstance(film!!.id,1)
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
