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
import com.example.misa.iwatch.ui.adapters.DiffusionAdapter
import com.example.misa.iwatch.entity.Diffusion

/**
 * Created by misa on 3/29/18.
 */
class DiffusionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val diffusion = this.arguments?.getSerializable("diffusion") as ArrayList<Diffusion>
        val rootView = inflater.inflate(R.layout.fragment_diffusion, container, false)
        val rv = rootView.findViewById<RecyclerView>(R.id.recycleViewDiffusion)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)



        var adapter = DiffusionAdapter(diffusion)
        rv.adapter = adapter


        return rootView
    }

    companion object {

        fun newInstance( diffusion: ArrayList<Diffusion>): DiffusionFragment {

            val args = Bundle()

            args.putSerializable("diffusion", diffusion)

            val fragment = DiffusionFragment()
            fragment.arguments = args
            return fragment
        }
    }


}