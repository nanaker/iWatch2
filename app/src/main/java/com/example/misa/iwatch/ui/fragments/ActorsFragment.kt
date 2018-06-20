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
import com.example.misa.iwatch.Repository.sieries.SaisonDetailRepository
import com.example.misa.iwatch.entity.CreditsResponse
import com.example.misa.iwatch.ui.adapters.ActorsAdapter
import com.example.misa.iwatch.entity.Personnes
import com.example.misa.iwatch.entity.associate_Actors
import com.example.misa.iwatch.ui.ViewModels.SaisonsDetailViewModel
import com.example.misa.iwatch.ui.adapters.ActorsDetailAdapter
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by misa on 3/27/18.
 */
class ActorsFragment : Fragment() {
    var actors= ArrayList<associate_Actors>()
    lateinit var rv:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_actors, container, false)
        val nb_saison = this.arguments?.getInt("nb_saison")!!
        val id_serie = this.arguments?.getInt("id_serie")!!

        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILSAISON) as SaisonDetailRepository

        val DetailSaisonModel =  SaisonsDetailViewModel(repo)
        DetailSaisonModel.getcredits(id_serie,nb_saison)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)



        rv = rootView.findViewById<RecyclerView>(R.id.recycleViewActors)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)




        return rootView
    }
    private fun handleResponse(credits: CreditsResponse) {
        println("response credits")
        println("response credits"+credits)

        this.actors=credits.associate_Actors
        println("handle response size"+credits.associate_Actors.size)
        var adapter_actor = ActorsAdapter(actors)
        rv.adapter = adapter_actor
        println("invalidate credits")
        this.rv.invalidate();



    }
    private fun handleError(error: Throwable) { Log.d("error actors saison ", error.localizedMessage) }

    companion object {

        fun newInstance( id:Int ,nb:Int): ActorsFragment {

            val args = Bundle()

            args.putInt("id_serie", id)
            args.putInt("nb_saison", nb)

            val fragment = ActorsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}