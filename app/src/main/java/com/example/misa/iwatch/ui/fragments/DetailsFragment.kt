package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.entity.*
import com.example.misa.iwatch.ui.adapters.ActorsDetailAdapter
import com.example.misa.iwatch.ui.adapters.AssociateFilmAdapter
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by misa on 3/29/18.
 */
class DetailsFragment: android.support.v4.app.Fragment() {
    lateinit var  associate_Actors:ArrayList<associate_Actors>
    lateinit var  associate_film:ArrayList<associate_Movie>
    lateinit var rv_actor:RecyclerView
    lateinit var rv_associateFilm:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val id = this.arguments?.getInt("id_movie")!!
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository

        val DetailFilmModel =  MoviesDetailViewModel(repo)
        DetailFilmModel.getcredits(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseCredits, this::handleErrorCredits)

        DetailFilmModel.getsimilar(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseSimilar, this::handleErrorSimilar)




        val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        this.rv_actor = rootView.findViewById<RecyclerView>(R.id.RecycleViewActorDetail)
        this.rv_actor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        this.rv_associateFilm = rootView.findViewById<RecyclerView>(R.id.RecycleViewAssociateFilm)
        this.rv_associateFilm.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return rootView
    }

    private fun handleResponseCredits(credits: CreditsResponse) {
        println("response credits")
        println("response credits"+credits)

        this.associate_Actors=credits.associate_Actors
        println("handle response size"+credits.associate_Actors.size)
        var adapter_actor = ActorsDetailAdapter(associate_Actors)
        rv_actor.adapter = adapter_actor
        println("invalidate credits")
        this.rv_actor.invalidate();



    }
    private fun handleResponseSimilar(similar: SimilarMovieResponse) {
        this.associate_film=similar.associate_Movie
        println("handle response size"+this.associate_film.size)
        var adapter_associateFil = AssociateFilmAdapter(associate_film)
        rv_associateFilm.adapter = adapter_associateFil
        println("invalidate film")
        this.rv_associateFilm.invalidate();


    }

    private fun handleErrorCredits(error: Throwable) { Log.d("error associate actors ", error.localizedMessage) }
    private fun handleErrorSimilar(error: Throwable) { Log.d("error similar movies", error.localizedMessage) }




    companion object {

        fun newInstance(id: Int): DetailsFragment {

            val args = Bundle()

            args.putInt("id_movie", id)

            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}