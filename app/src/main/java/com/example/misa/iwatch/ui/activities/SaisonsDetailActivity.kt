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
import com.example.misa.iwatch.Repository.sieries.SaisonDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Saisons
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.ui.ViewModels.SaisonsDetailViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesDetailViewModel
import com.example.misa.iwatch.ui.fragments.*
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_saison_detail.*
import kotlinx.android.synthetic.main.activity_serie_detail.*

class SaisonsDetailActivity : AppCompatActivity() {
    lateinit var saison :Saisons
    var id_serie:Int=0
    var nb_saison:Int=0;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saison_detail)
        setTitle("Saisons")
        val intent = this.intent
        val bundle = intent.extras

         nb_saison = bundle!!.getInt("id_saison")
         id_serie=bundle!!.getInt("id_serie")

        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILSAISON) as SaisonDetailRepository

        val DetailSaisonModel =  SaisonsDetailViewModel(repo)
        DetailSaisonModel.getsaison(id_serie,nb_saison+1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)



    }

    private fun handleResponse(saison: Saisons) {
        println(" saison handle result ")
        this.saison=saison
        println("saison name "+saison.titre)
        println(" nb saison "+saison.nbsaison)
        println(" image path "+saison!!.image)
        if (saison.image!=null){
        Glide.with(this)
                .load(WebServiceFactory.IMAGE_BASE_URL +saison!!.image)
                .into(picturesaison)}
        println("pass image ")

        title_saison_detail.text= saison.titre
        println("pass titre ")
        dateOnAir.text= saison.date
        println("pass date ")
        nbeisodessaisosn.text=saison.episode[saison.episode.size-1].nb_episode.toString()
        println("pass nbepisodes")
        storyLine_saison.text= saison.storyline
        println("pass story "+saison.storyline)
        println("pass nb saison  "+saison.nbsaison)
        nbsaison.text= nb_saison.toString()
        println("pass nb saison  "+saison.nbsaison)

        /****************************************** Add Video *************************************/

       TODO("ADD VIDEO")
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = saison.titre





        setTitle(saison!!.titre)

        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

         pageAdapter.addFragment(EpisodeFragment.newInstance(saison!!.episode), "EPISODE")
        pageAdapter.addFragment(ActorsFragment.newInstance(id_serie,nb_saison+1), "ACTORS")



        saisonContainer.adapter = pageAdapter
        Saisontabs.setupWithViewPager(saisonContainer)


    }

    private fun handleError(error: Throwable) { Log.d("error saison load ", error.localizedMessage) }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }


}
