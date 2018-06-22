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
        if (episode.storyline!=null)storyLine_eposide.text=episode.storyline
        if (episode.nb_episode!=null)numepisode.text=episode.nb_episode.toString()
        if (episode.date!=null) datesortieEpisodedetail.text=episode.date
        if (episode.eval!=null)rateepisode.text=episode.eval.toString().substring(0,3)
        if (episode.eval!=null)rating_episode.rating=episode.eval
        if (episode.image!=null){
            Glide.with(this)
                    .load(WebServiceFactory.IMAGE_BASE_URL+episode!!.image)
                    .into(pictureEpisodeDetail)}


        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = episode.nom


        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_annonce_episode.setMediaController(mediaController)

        try {
            bande_annonce_episode.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video_harry))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


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
