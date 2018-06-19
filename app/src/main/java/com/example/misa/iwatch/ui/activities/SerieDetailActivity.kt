package com.example.misa.iwatch.ui.activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.entity.data
import com.example.misa.iwatch.ui.fragments.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_serie_detail.*
import java.util.ArrayList

class SerieDetailActivity : AppCompatActivity() {
    lateinit var serie: Series
    var index:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_detail)
        val intent = this.intent
        val bundle = intent.extras

         serie = bundle!!.getSerializable("serie") as Series
        index=bundle!!.getInt("index_serie")

        //pictureSerie.setImageResource(serie!!.image)
        title_serie_detail.text= serie!!.titre
        details_serie.text= serie!!.info
       // directorName_serie.text= serie!!.
        storyLine_serie.text= serie!!.info
        if(serie!!.fav) serieFavori.isFavorite = true

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = serie.titre


        rateResultSerie.visibility = View.GONE
        rating_serie.rating  = serie!!.voteAverage

        setTitle(serie!!.titre)
        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_annonce_serie.setMediaController(mediaController)

        try {
            bande_annonce_serie.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + serie.video))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(SaisonsFragment.newInstance(serie!!.saisons), "SAISONS")
        pageAdapter.addFragment(SeriesLFragment.newInstance(serie!!.seriesliees), "SERIES LIEES")
     //   pageAdapter.addFragment(CommentsFragment.newInstance(serie!!.comments), "COMMENTS")


        SeriesContainer.adapter = pageAdapter
        Seriestabs.setupWithViewPager(SeriesContainer)

        addfav_serie()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }



    fun moy(eval: ArrayList<Float>):Float{
        var star:Float= 0.0F

        for (value in eval ){
            star=star+value
        }
        star=star/eval.size

        return star;

    }

    fun addfav_serie() {
        serie.fav=true
        // set the function to add a favoris film
        data.SeriesFav.add(serie)


    }
}
