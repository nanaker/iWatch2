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
import com.example.misa.iwatch.ui.adapters.EpisodeAdapter
import com.example.misa.iwatch.entity.Episode

/**
 * Created by misa on 3/27/18.
 */
class EpisodeFragment : Fragment() {
    var episodes= ArrayList<Episode>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_episodes, container, false)
        val episodes = this.arguments?.getSerializable("episodes")as ArrayList<Episode>
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewEpisodes)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)


        var adapter = EpisodeAdapter(episodes)
        rv.adapter = adapter
        return rootView
    }
    companion object {

        fun newInstance( episodes : ArrayList<Episode>): EpisodeFragment {

            val args = Bundle()

            args.putSerializable("episodes", episodes)

            val fragment = EpisodeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}