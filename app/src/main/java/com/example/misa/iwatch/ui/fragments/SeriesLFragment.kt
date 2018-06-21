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
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.ReviewResponse
import com.example.misa.iwatch.ui.adapters.SeriesLAdapter
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.entity.SimilarSerieResponse
import com.example.misa.iwatch.entity.associate_series
import com.example.misa.iwatch.ui.ViewModels.SeriesDetailViewModel
import com.example.misa.iwatch.ui.adapters.CommentAdapter
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by misa on 3/27/18.
 */
class SeriesLFragment : Fragment() {
    var seriesLiee= ArrayList<associate_series>()
    lateinit var rv:RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_series_liee, container, false)
        val id= this.arguments?.getInt("id_serie")!!
        val repo = ServiceLocator.instance()
                .getRepository(IRepository.Type.DETAILSERIE) as SerieDetailRepository

        val DetailSerieModel =  SeriesDetailViewModel(repo)
        DetailSerieModel.getsimilar(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)

        rv = rootView.findViewById<RecyclerView>(R.id.recycleViewSeriesLiees)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)



        return rootView
    }
    private fun handleResponse(seriesliees: SimilarSerieResponse) {
        this.seriesLiee = seriesliees.associate_series
        println("serie liees size " + seriesLiee.size)
        var adapter = SeriesLAdapter(seriesLiee)
        rv.adapter = adapter
        this.rv.invalidate();
    }
    private fun handleError(error: Throwable) { Log.d("error serie liees load ", error.localizedMessage) }

    companion object {

        fun newInstance( id : Int): SeriesLFragment {

            val args = Bundle()

            args.putInt("id_serie", id)

            val fragment = SeriesLFragment()
            fragment.arguments = args
            return fragment
        }
    }
}