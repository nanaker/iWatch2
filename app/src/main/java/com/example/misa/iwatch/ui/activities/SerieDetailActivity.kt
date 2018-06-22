package com.example.misa.iwatch.ui.activities

import android.annotation.SuppressLint
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.entity.data
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesDetailViewModel
import com.example.misa.iwatch.ui.fragments.*
import com.example.misa.iwatch.utils.DefaultServiceLocator
import com.example.misa.iwatch.utils.ServiceLocator
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerInitListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_serie_detail.*
import java.util.ArrayList

class SerieDetailActivity : AppCompatActivity() {
    lateinit var serie: Series
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)
        val intent = this.intent
        val bundle = intent.extras


        id=bundle!!.getInt("id_serie")
        println("movie id activity "+id)

        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILSERIE) as SerieDetailRepository

        val DetailSerieModel =  SeriesDetailViewModel(repo)

        DetailSerieModel.getserie(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.from(ServiceLocator.instance().getNetworkExecutor()))
                .subscribe(this::handleResponse, this::handleError)

        //pictureSerie.setImageResource(serie!!.image)


    }
    @SuppressLint("CheckResult")
    private fun handleResponse(serie: Series) {
        this.serie=serie
        title_serie_detail.text= serie!!.titre
        details_serie.text= serie!!.genres[0].name +" , "+serie!!.genres[1].name
        directorName_serie.text= serie!!.date
        nbeisodes.text=serie!!.nbEposides
        nbsaisons.text=serie!!.nbSaisons
        storyLine_serie.text= serie!!.info
        if(serie!!.fav) serieFavori.isFavorite = true
        if (serie.image!=null){
        Glide.with(this)
                .load(serie!!.image)
                .into(pictureSerie)}
        rateSerie.text=serie.voteAverage.toString()

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = serie.titre
        println("saisons")
        println("nb de saisons"+serie.saisons.size)


        rating_serie.rating  = serie!!.voteAverage

        setTitle(serie!!.titre)
        /****************************************** Add Video *************************************/

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        ServiceLocator.instance()
                .getApi()
                .getSeriesVideo(serie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.from(ServiceLocator.instance().getNetworkExecutor()))
                .subscribe(
                        {
                            Log.d("TAG",it.results[0].key)
                            youTubePlayerView.initialize(object : YouTubePlayerInitListener {
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

        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(SaisonsFragment.newInstance(serie!!.saisons,serie.id), "SAISONS")
          pageAdapter.addFragment(SeriesLFragment.newInstance(serie!!.id), "SERIES LIEES")
           pageAdapter.addFragment(CommentsFragment.newInstance(serie!!.id,2), "COMMENTS")


        SeriesContainer.adapter = pageAdapter
        Seriestabs.setupWithViewPager(SeriesContainer)


    }
    private fun handleError(error: Throwable) { Log.d("error serie load ", error.localizedMessage) }




    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }





    fun addfav_serie() {
        serie.fav=true
        // set the function to add a favoris film
        data.SeriesFav.add(serie)


    }
}
