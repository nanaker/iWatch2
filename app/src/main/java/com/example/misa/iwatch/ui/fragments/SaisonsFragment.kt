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
import com.example.misa.iwatch.ui.adapters.SaisonsAdapter
import com.example.misa.iwatch.entity.Saisons

/**
 * Created by misa on 3/27/18.
 */
class SaisonsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_saisons, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewSaisons)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        val saisons = this.arguments?.getSerializable("saison")as ArrayList<Saisons>


        var adapter = SaisonsAdapter(saisons)
        rv.adapter = adapter
        return rootView
    }
    companion object {

        fun newInstance( saisons : ArrayList<Saisons>): SaisonsFragment {

            val args = Bundle()

            args.putSerializable("saison", saisons)

            val fragment = SaisonsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}