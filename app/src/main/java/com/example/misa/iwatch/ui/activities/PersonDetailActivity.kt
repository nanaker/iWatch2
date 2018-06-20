package com.example.misa.iwatch.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.MenuItem
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.MovieSectionsPageAdapter
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.ui.fragments.CommentsFragment
import com.example.misa.iwatch.ui.fragments.FilmographieFragment
import kotlinx.android.synthetic.main.activity_person_detail.*
import java.util.ArrayList

class PersonDetailActivity : AppCompatActivity() {

    lateinit var personnes: Personnes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_detail)
        setTitle("Details")

        val intent = this.intent
        val bundle = intent.extras
        personnes = bundle!!.getSerializable("personne") as Personnes

        setTitle(personnes!!.nom)
        //picturePersonneDetail.setImageResource(personnes!!.image)
        actorNameDetail.text=personnes!!.nom
        birthplace.text=personnes!!.LieuNiassance
        birthdate.text=personnes!!.dateNaissance
        bibliographie.text=personnes!!.bibliographie
        rating_personne.rating=personnes!!.eval
        picturePersonneDetail.setImageResource(personnes!!.image2)

        rateResultPerson.visibility = View.GONE

        val pageAdapter = MovieSectionsPageAdapter(supportFragmentManager)

        pageAdapter.addFragment(FilmographieFragment.newInstance(personnes!!.filmographie), "FILMOGRAPHIE")
      //  pageAdapter.addFragment(CommentsFragment.newInstance(personnes!!.comments), "COMMENTS")

       PersonneContainer.adapter = pageAdapter
        Personnetabs.setupWithViewPager(PersonneContainer)



        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = personnes.nom
    }

  /*  fun rateMe_personne(view: View) {

        personnes!!.eval.add(rating_personne.rating)
        rating_personne.rating=moy(personnes!!.eval)
        submit_personne.visibility= View.GONE
        submit_personne.isEnabled=false

        rateResultPerson.visibility = View.VISIBLE
        ratePerson.text = moy(personnes!!.eval).toString().substring(0,3)
    }*/

    fun moy(eval: ArrayList<Float>):Float{
        var star:Float= 0.0F

        for (value in eval ){
            star=star+value
        }
        star=star/eval.size

        return star;

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when(id){
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }

}
