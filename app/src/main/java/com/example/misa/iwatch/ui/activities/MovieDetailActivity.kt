package com.example.misa.iwatch.ui.activities

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
import kotlin.collections.ArrayList
import com.example.misa.iwatch.entity.data
import kotlinx.android.synthetic.main.activity_movie_detail.*


class MovieDetailActivity : AppCompatActivity() {

   lateinit var film: Movie
     var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val intent = this.intent
        val bundle = intent.extras


        film = bundle!!.getSerializable("film") as Movie
        index=bundle!!.getInt("index_movie")

        //picturePersonne.setImageResource(film!!.image)
        title_movie_detail.text= film!!.title
        details_movie.text= film!!.info
        directorName_detail.text= film!!.directeur
        storyLine.text= film!!.storyline
        if(film!!.fav) movieFavori.isFavorite = true

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = film.title

        rateResult.visibility = View.GONE

        rating_movie.rating = film!!.voteAverage

        setTitle("Details")

        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(DetailsFragment.newInstance(film!!), "DETAILS")
        pageAdapter.addFragment(RoomsFragment.newInstance(film!!.room), "ROOMS")
        pageAdapter.addFragment(CommentsFragment.newInstance(film!!.comments), "COMMENTS")


        movieContainer.adapter = pageAdapter
        Movietabs.setupWithViewPager(movieContainer)


        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_anonce.setMediaController(mediaController)

        try {
            bande_anonce.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + film.video))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        addfav_film()


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


    fun rateMe(view: View) {

       // data.Films[index].voteAverage.add(rating_movie.rating)

        //film!!.voteAverage.add(rating_movie.rating)
        //rating_movie.rating = moy(film!!.voteAverage)
        submit.visibility = View.GONE
        rateResult.visibility = View.VISIBLE
       // rate.text = moy(film!!.voteAverage).toString().substring(0,3)
    }

    fun moy(eval: ArrayList<Float>):Float{
        var star:Float= 0.0F

        for (value in eval ){
            star = star + value
        }
        star=star/eval.size

        return star;

    }

    fun addfav_film() {
        // set the function to add a favoris film
        film.fav = true
        data.Filmsfav.add(film)


    }




}
