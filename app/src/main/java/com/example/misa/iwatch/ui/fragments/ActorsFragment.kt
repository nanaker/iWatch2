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
import com.example.misa.iwatch.ui.adapters.ActorsAdapter
import com.example.misa.iwatch.entity.Personnes

/**
 * Created by misa on 3/27/18.
 */
class ActorsFragment : Fragment() {
    var actors= ArrayList<Personnes>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_actors, container, false)
        val actors = this.arguments?.getSerializable("actors_episode")as ArrayList<Personnes>
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewActors)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)


        var adapter = ActorsAdapter(actors)
        rv.adapter = adapter
        return rootView
    }
    companion object {

        fun newInstance( actor : ArrayList<Personnes>): ActorsFragment {

            val args = Bundle()

            args.putSerializable("actors_episode", actor)

            val fragment = ActorsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}