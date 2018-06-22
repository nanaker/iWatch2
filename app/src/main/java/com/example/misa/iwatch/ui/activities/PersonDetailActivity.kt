package com.example.misa.iwatch.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.actors.ActorDetailRepository
import com.example.misa.iwatch.api.WebServiceFactory
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.ui.ViewModels.ActorDetailViewModel
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.ui.fragments.CommentsFragment
import com.example.misa.iwatch.ui.fragments.FilmographieFragment
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_person_detail.*
import java.util.ArrayList

class PersonDetailActivity : AppCompatActivity() {

    lateinit var personnes: Personnes
    var id=0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        setTitle("Details")

        val intent = this.intent
        val bundle = intent.extras
        id = bundle!!.getInt("id_personne")
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILPERSONNE) as ActorDetailRepository

        val DetailActorModel =  ActorDetailViewModel(repo)
        DetailActorModel.getactor(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)




    }


    private fun handleResponse(personne: Personnes) {
        this.personnes=personne
        setTitle(personnes!!.nom)
        Glide.with(this)
                .load(personne!!.image)
                .into(picturePersonneDetail)


        actorNameDetail.text=personnes!!.nom
        if (personnes.LieuNiassance!=null)   birthplace.text=personnes!!.LieuNiassance
        if (personnes.dateNaissance!=null)  birthdate.text=personnes!!.dateNaissance
        if (personnes.bibliographie!=null) bibliographie.text=personnes!!.bibliographie
        if (personnes.eval!=null) poularity.text=personnes.eval.toString()



        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(FilmographieFragment.newInstance(personnes!!.id), "FILMOGRAPHIE")


        PersonneContainer.adapter = pageAdapter
        Personnetabs.setupWithViewPager(PersonneContainer)



        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = personnes.nom


    }
    private fun handleError(error: Throwable) { Log.d("error personne load ", error.localizedMessage) }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }

}
