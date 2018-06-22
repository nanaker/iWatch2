package com.example.misa.iwatch.ui.activities

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
    private fun handleResponse(serie: Series) {
        this.serie=serie
        title_serie_detail.text= serie!!.titre
        if (serie.genres.size>0)details_serie.text= serie!!.genres[0].name +" , "+serie!!.genres[1].name
        if (serie.date!=null)directorName_serie.text= serie!!.date
        if (serie.nbEposides!=null)nbeisodes.text=serie!!.nbEposides
        if (serie.nbSaisons!=null)nbsaisons.text=serie!!.nbSaisons
        if (serie.info!=null)storyLine_serie.text= serie!!.info
        if(serie!!.fav) serieFavori.isFavorite = true
        if (serie.image!=null){
        Glide.with(this)
                .load(serie!!.image)
                .into(pictureSerie)}
        if (serie.voteAverage!=null)rateSerie.text=serie.voteAverage.toString()

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = serie.titre



        if (serie.voteAverage!=null)rating_serie.rating  = serie!!.voteAverage

        setTitle(serie!!.titre)
        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_annonce_serie.setMediaController(mediaController)

        try {
            bande_annonce_serie.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video_harry))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


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
