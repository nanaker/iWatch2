package com.example.misa.iwatch.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.ui.adapters.SeriesAdapter
import com.example.misa.iwatch.entity.data.Companion.getSeriesRecent
import com.example.misa.iwatch.ui.ViewModels.MoviesViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesViewModel
import com.example.misa.iwatch.ui.adapters.MoviesAdapter
import com.example.misa.iwatch.utils.getViewModel

/**
 * Created by misa on 3/27/18.
 */
class SeriesFragment : Fragment() {

    private val seriesAdapter by lazy {
        SeriesAdapter(context!!)
    }

    private lateinit var rv:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_series, container, false)
        rv = rootView.findViewById(R.id.recycleViewSeries)



        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val seriesViewModel = getViewModel(IRepository.Type.SERIES) as SeriesViewModel
        seriesViewModel.popularSeries.observe(this, Observer<PagedList<Series>> {
            seriesAdapter.submitList(it)
        })
        rv.adapter = seriesAdapter
    }
}