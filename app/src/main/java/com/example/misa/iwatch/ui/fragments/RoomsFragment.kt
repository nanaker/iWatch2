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
import com.example.misa.iwatch.ui.adapters.RoomAdapter
import com.example.misa.iwatch.entity.Room
import com.example.misa.iwatch.entity.data.Companion.getMoviesRecent

/**
 * Created by misa on 3/29/18.
 */
class RoomsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val cinema = this.arguments?.getSerializable("rooms") as ArrayList<Room>
        val rootView = inflater.inflate(R.layout.fragment_rooms, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewRooms)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        val Films= getMoviesRecent()

        var adapter = RoomAdapter(cinema)
        rv.adapter = adapter


        return rootView
    }

    companion object {

        fun newInstance( cinema: ArrayList<Room>): RoomsFragment {

            val args = Bundle()

            args.putSerializable("rooms", cinema)

            val fragment = RoomsFragment()
            fragment.arguments = args
            return fragment
        }
    }


}