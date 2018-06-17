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
import com.example.misa.iwatch.entity.Saisons
import com.example.misa.iwatch.ui.fragments.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_saison_detail.*
import java.util.ArrayList

class SaisonsDetailActivity : AppCompatActivity() {
    lateinit var saison :Saisons



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saison_detail)
        setTitle("Saisons")
        val intent = this.intent
        val bundle = intent.extras

        saison = bundle!!.getSerializable("saison_detail") as Saisons


        picturesaison.setImageResource(saison!!.image)
        title_saison_detail.text= saison!!.titre
        details_saison.text= saison!!.info

        storyLine_saison.text= saison!!.storyline

        /****************************************** Add Video *************************************/

        val mediaController = MediaController(this)

        mediaController.setAnchorView(bande_anonce)
        bande_annonce_saison.setMediaController(mediaController)

        try {
            bande_annonce_saison.setVideoURI(Uri.parse( "android.resource://" + getPackageName() + "/" + saison.video))

        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }

        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = saison.titre


        rateResult_saison.visibility = View.GONE
        rating_saison.rating=moy(saison!!.eval)

        setTitle(saison!!.titre)

        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(EpisodeFragment.newInstance(saison!!.episode), "EPISODE")
        pageAdapter.addFragment(ActorsFragment.newInstance(saison!!.actors), "ACTORS")
        pageAdapter.addFragment(CommentsFragment.newInstance(saison!!.comments), "COMMENTS")


        saisonContainer.adapter = pageAdapter
        Saisontabs.setupWithViewPager(saisonContainer)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }
    fun rateMe_saison(view: View) {
        saison!!.eval.add(rating_saison.rating)
        rating_saison.rating=moy(saison!!.eval)
        submit_saison.visibility= View.GONE


        rateResult_saison.visibility = View.VISIBLE
        rate_saison.text = moy(saison!!.eval).toString().substring(0,3)
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
