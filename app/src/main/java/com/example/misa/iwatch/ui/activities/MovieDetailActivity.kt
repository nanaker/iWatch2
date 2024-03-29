package com.example.misa.iwatch.ui.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.ui.fragments.CommentsFragment
import com.example.misa.iwatch.ui.fragments.DetailsFragment
import com.example.misa.iwatch.ui.fragments.RoomsFragment
import android.view.MenuItem
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.entity.*
import com.example.misa.iwatch.room.filmdb.filmDataBase
import com.example.misa.iwatch.room.filmdb.modal.film
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import com.pierfrancescosoffritti.youtubeplayer.player.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.io.File
import java.io.FileOutputStream
import java.lang.ref.WeakReference
import java.net.URL




class MovieDetailActivity : AppCompatActivity() {

    lateinit var film: Movie
    lateinit var film_db:film
    var id: Int = 0
    var tag:String=""

    lateinit var fragmentDetail:DetailsFragment
    lateinit var fragmentRoom:RoomsFragment
    var fragmentComments:CommentsFragment? = null

    // variables for room data base
    private var filmDbInstance: filmDataBase ? = null
    lateinit var filmFav: film


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = this.intent
        val bundle = intent.extras


        id = bundle!!.getInt("id_movie")
        println("movie id activity "+id)
        tag =bundle!!.getString("tag")
        when(tag) {
            WebServiceFactory.TAG_BDD -> {
                film_db =bundle!!.getSerializable("film") as film


                title_movie_detail.text= film_db!!.title
                if (film_db.genres!!.size>1){ details_movie.text= film_db!!.genres!![0].name +" , "+ film_db!!.genres!![1].name }
                else if (film_db.genres!!.size>0 ) details_movie.text= film_db!!.genres!![0].name
                if (film_db.release_date!=null) directorName_detail.text= film_db!!.release_date
                if (film_db.voteAverage!=null) rating_movie.rating = film_db!!.voteAverage
                if (film_db.info!=null)storyLine.text= film_db!!.info
                //Charger l'image
                val photoPath = Environment.getExternalStorageDirectory().toString() + "/"+film_db.id.toString()+".jpg"
                val bitmap = BitmapFactory.decodeFile(photoPath)
                pictureMovieDetail.setImageBitmap(bitmap)
                rate.text= film_db.voteAverage.toString()
                movieFavori.isFavorite = true

               

                setSupportActionBar(findViewById(R.id.my_toolbar))
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = film_db.title

                setTitle("Details")

                val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)
                fragmentDetail=DetailsFragment.newInstance(film_db!!.id)
                fragmentRoom=RoomsFragment.newInstance();
                fragmentComments=CommentsFragment.newInstance(film_db!!.id,1)
                pageAdapter.addFragment(fragmentDetail, "DETAILS")
                pageAdapter.addFragment(fragmentRoom, "ROOMS")
                pageAdapter.addFragment(fragmentComments!!, "COMMENTS")

                movieContainer.adapter = pageAdapter
                Movietabs.setupWithViewPager(movieContainer)


            }
                WebServiceFactory.TAG_API  ->{
                    val repo = ServiceLocator.instance()
                            .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository

                    val DetailFilmModel =  MoviesDetailViewModel(repo)

                    DetailFilmModel.getmovie(id)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(this::handleResponse, this::handleError)

                }
        }




        filmDbInstance = filmDataBase.getInstance(this)

      movieFavori.setOnClickListener(){

          movieFavori.isFavorite = true
          film.fav = true



          filmFav = film(film.id, film.title, film.info, film.release_date,film.genres,null,
                  null, null,"", film.voteAverage, film.image)
          InsertTask(context = this, film = filmFav).execute()

          val j = film.genres!!.size
          Log.e("ID ", "Siiiize Genres: $j")
          setResult(filmFav,2)

          saveComment(film.id)
          saveAssociatefilm(film.id)
          saveAssociateActeur(film.id)

      }

    }

    private fun saveAssociateActeur(id: Int) {
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository
        val DetailFilmModel =  MoviesDetailViewModel(repo)

        DetailFilmModel.getcredits(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseCredits, this::handleError)

    }

    fun handleResponseCredits(credits:CreditsResponse){
        val associate_Actors = credits.associate_Actors
        val id_movie = film.id

        // save associate actors in the database
        // save associate film in the database
        filmFav.actors = credits.associate_Actors
        filmDbInstance!!.getFilmDao().updateFilm(filmFav)
        val  j = filmDbInstance!!.getFilmDao().getFilmFav()[0].actors!!.size
        Log.e("ID ", "Siiiize Actors: $j")
        setResult(filmFav,2)



    }

    private fun saveAssociatefilm(id: Int) {
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository
        val DetailFilmModel =  MoviesDetailViewModel(repo)

        DetailFilmModel.getsimilar(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSimilar, this::handleError)
    }

    fun handleResponseSimilar(similar:SimilarMovieResponse){
        val associate_film = similar.associate_Movie
        val id_movie = film.id

        // save associate film in the database
        filmFav.associatefilm = similar.associate_Movie
        filmDbInstance!!.getFilmDao().updateFilm(filmFav)
        val  j = filmDbInstance!!.getFilmDao().getFilmFav()[0].associatefilm!!.size
        Log.e("ID ", "Siiiize Mooovie: $j")
        setResult(filmFav,2)


    }


    private fun saveComment(id: Int) {
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository
        val DetailFilmModel =  MoviesDetailViewModel(repo)

        DetailFilmModel.getreview(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseComment, this::handleError)

    }
    fun handleResponseComment(reviews:ReviewResponse){
        //val comments = reviews.comments
        val id_movie = film.id

        // save reviews in the database

        filmFav.comments = reviews.comments
        filmDbInstance!!.getFilmDao().updateFilm(filmFav)
        val  j = filmDbInstance!!.getFilmDao().getFilmFav()[0].comments!!.size
        Log.e("ID ", "Siiiize Comments: $j")
        setResult(filmFav,2)



    }


    private fun setResult(film: film, flag: Int) {
        setResult(flag, Intent().putExtra("filmfav", film.id))
        //finish()
    }

    private class InsertTask// only retain a weak reference to the activity
    internal constructor(context: MovieDetailActivity, private val film: film) : AsyncTask<Void, Void, Boolean>() {

        private var activityReference: WeakReference<MovieDetailActivity>

        init {
            activityReference = WeakReference<MovieDetailActivity>(context)
        }

        // doInBackground methods runs on a worker thread
        override fun doInBackground(vararg objs: Void): Boolean? {
            // retrieve auto incremented film id
            val j = activityReference.get()!!.filmDbInstance!!.getFilmDao().addFilmFav(film)
            film.id = j.toInt()
            Log.e("ID ", "doInBackground: $j")
            //saveImage(film.image)

            //save the image of the movie
            val url = URL(film.image)
            Log.e("ID ", "URL: $url")
            val input = url.openStream()
            try {
                //The sdcard directory e.g. '/sdcard' can be used directly, or
                //more safely abstracted with getExternalStorageDirectory()
                val storagePath = Environment.getExternalStorageDirectory()
                println("storage path "+storagePath)
                val output = FileOutputStream(File(storagePath, film.id.toString()+".jpg"))
                try {
                    val buffer = ByteArray(2048)
                    var bytesRead = 0
                    bytesRead = input.read(buffer, 0, buffer.size)
                    while ((bytesRead) >= 0) {
                        output.write(buffer, 0, bytesRead)
                        bytesRead = input.read(buffer, 0, buffer.size)
                    }
                } finally {
                    output.close()
                }
            } finally {
                input.close()
            }

            return true

        }

        // onPostExecute runs on main thread
        override fun onPostExecute(bool: Boolean?) {

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
        film.comments = fragmentComments?.comments !!
        println("comments size " + film.comments.size + " " + film.comments)
        film.associatefilm = fragmentDetail.associate_film
        println("associate film  size " + film.associatefilm.size + " " + film.associatefilm)
        film.actors = fragmentDetail.associate_Actors
        film.room = data.getCinema()
        data.Filmsfav.add(film)

    }
    @SuppressLint("CheckResult")
    private fun handleResponse(movie: Movie) {
        this.film = movie
        title_movie_detail.text= film!!.title
      storyLine.text= film!!.info
        if (film.genres.size>1){ details_movie.text= film!!.genres[0].name +" , "+film!!.genres[1].name }
        else if (film.genres.size>0 ) details_movie.text= film!!.genres[0].name
        if (film.release_date!=null) directorName_detail.text= film!!.release_date
        if (film.voteAverage!=null) rating_movie.rating = film!!.voteAverage
        if (film.info!=null)storyLine.text= film!!.info
        if (film.image!=null){
        Glide.with(this)
               .load(film!!.image)
               .into(pictureMovieDetail)}
        rate.text= film.voteAverage.toString()
        rating_movie.rating=film.voteAverage


       film.fav=false
        val filmsfav=filmDbInstance!!.getFilmDao().getFilmFav()
        for (item in filmsfav){
            if (item.id==film.id){
                film.fav=true
                movieFavori.isFavorite = true
                break;
            }
        }

       /****************************************** Add Video *************************************/
           val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
           ServiceLocator.instance()
                   .getApi()
                   .getMovieVideo(film.id)
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribeOn(Schedulers.from(ServiceLocator.instance().getNetworkExecutor()))
                   .subscribe(
                           {
                               Log.d("TAG",it.results[0].key)
                               youTubePlayerView.initialize(object : YouTubePlayerInitListener{
                                   override fun onInitSuccess(youTubePlayer: YouTubePlayer) {
                                       youTubePlayer.addListener(object: AbstractYouTubePlayerListener(){
                                           override fun onReady() {
                                               youTubePlayer.loadVideo(it.results[0].key, 0.0F)
                                           }
                                       })
                                   }
                               }, true)
                           },
                           {
                               Log.d("TAG",it.message)
                           }
                   )





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
       pageAdapter.addFragment(fragmentComments!!, "COMMENTS")

       println("page container")
       println("data cinema size "+data.getCinema().size)
       movieContainer.adapter = pageAdapter
       Movietabs.setupWithViewPager(movieContainer)

    }
    private fun handleError(error: Throwable) { Log.d("error movie load ", error.localizedMessage) }





}
