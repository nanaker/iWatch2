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
        storyLine_eposide.text=episode.storyline
        rateResult_episode.visibility = View.GONE

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = episode.nom


        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_annonce_episode.setMediaController(mediaController)

        try {
            bande_annonce_episode.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + episode.video))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(DiffusionFragment.newInstance(episode!!.diffusion), "DIFFUSION")
        pageAdapter.addFragment(CommentsFragment.newInstance(episode!!.comments), "COMMENTS")


        episodeContainer.adapter = pageAdapter
        Episodetabs.setupWithViewPager( episodeContainer)



    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }
    fun rateMe_episode(view: View) {

        episode!!.eval.add(rating_episode.rating)
        rating_episode.rating=moy(episode!!.eval)
        submit_episode.visibility= View.GONE


        rateResult_episode.visibility = View.VISIBLE
        rate_episode.text = moy(episode!!.eval).toString().substring(0,3)
    }

    fun moy(eval: ArrayList<Float>):Float{
        var star:Float= 0.0F

        for (value in eval ){
            star=star+value
        }
        star=star/eval.size

        return star;

    }


}
