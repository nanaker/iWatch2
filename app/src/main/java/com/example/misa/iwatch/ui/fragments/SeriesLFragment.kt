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
import com.example.misa.iwatch.ui.adapters.SeriesLAdapter
import com.example.misa.iwatch.entity.Series
import com.example.misa.iwatch.entity.associate_series
import com.example.misa.iwatch.entity.data.Companion.getSeriesRecent

/**
 * Created by misa on 3/27/18.
 */
class SeriesLFragment : Fragment() {
    var seriesLiee= ArrayList<Series>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_series_liee, container, false)
        val series = this.arguments?.getSerializable("Seriesliees") as ArrayList<associate_series>
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewSeriesLiees)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)


        var adapter = SeriesLAdapter(series)
        rv.adapter = adapter
        return rootView
    }
    companion object {

        fun newInstance( seriesL : ArrayList<associate_series>): SeriesLFragment {

            val args = Bundle()

            args.putSerializable("Seriesliees", seriesL)

            val fragment = SeriesLFragment()
            fragment.arguments = args
            return fragment
        }
    }
}