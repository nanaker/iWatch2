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
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Episode
import com.example.misa.iwatch.ui.fragments.CommentsFragment
import com.example.misa.iwatch.ui.fragments.DiffusionFragment
import kotlinx.android.synthetic.main.activity_episode_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.util.ArrayList

class EpisodeDetailActivity : AppCompatActivity() {

    lateinit var episode :Episode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val intent = this.intent
        val bundle = intent.extras

        episode = bundle!!.getSerializable("episode") as Episode

        setTitle(episode.nom)
        title_episode.text=episode.nom
        storyLine_eposide.text=episode.storyline
        numepisode.text=episode.nb_episode.toString()
        datesortieEpisodedetail.text=episode.date
        rateepisode.text=episode.eval.toString().substring(0,3)
        rating_episode.rating=episode.eval
        if (episode.image!=null){
            Glide.with(this)
                    .load(WebServiceFactory.IMAGE_BASE_URL+episode!!.image)
                    .into(pictureEpisodeDetail)}


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = episode.nom


        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)
        TODO("ADD VIDEO")



        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)









    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }





}
