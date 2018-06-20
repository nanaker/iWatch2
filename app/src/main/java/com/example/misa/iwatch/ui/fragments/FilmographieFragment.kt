package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.actors.ActorDetailRepository
import com.example.misa.iwatch.ui.adapters.*
import com.example.misa.iwatch.entity.*
import com.example.misa.iwatch.ui.ViewModels.ActorDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by misa on 3/27/18.
 */
class FilmographieFragment : Fragment() {
    lateinit var rv:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_filmographie, container, false)
       val id  = this.arguments?.getInt("id_personne")!!

        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILPERSONNE) as ActorDetailRepository

        val DetailActorModel =  ActorDetailViewModel(repo)
        DetailActorModel.getcreditsmovies(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)

        rv = rootView.findViewById<RecyclerView>(R.id.recycleViewFilmographie)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)





        return rootView
    }
    private fun handleResponse(associate_movies: CreditsMovieActorResponse) {
         println("associate film size "+associate_movies.associate_Movie_actors)
        var adapter = FilmographieAdapter(associate_movies.associate_Movie_actors)
        rv.adapter = adapter
        rv.invalidate()

    }
    private fun handleError(error: Throwable) { Log.d("error filmographie  ", error.localizedMessage) }

    companion object {

        fun newInstance( id : Int): FilmographieFragment {

            val args = Bundle()

            args.putSerializable("id_personne", id)

            val fragment = FilmographieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}