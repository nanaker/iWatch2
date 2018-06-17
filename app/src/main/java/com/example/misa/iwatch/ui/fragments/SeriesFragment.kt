package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.misa.iwatch.R
import com.example.misa.iwatch.ui.adapters.SeriesAdapter
import com.example.misa.iwatch.entity.data.Companion.getSeriesRecent

/**
 * Created by misa on 3/27/18.
 */
class SeriesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_series, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewSeries)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Series= getSeriesRecent()
       // var adapter = SeriesAdapter(Series)
        //rv.adapter = adapter
        return rootView
    }
}