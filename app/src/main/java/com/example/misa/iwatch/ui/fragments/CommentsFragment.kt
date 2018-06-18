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
import com.example.misa.iwatch.ui.adapters.CommentAdapter
import com.example.misa.iwatch.entity.Comments

import android.view.inputmethod.EditorInfo
import android.widget.EditText
//import com.sun.deploy.ui.CacheUpdateProgressDialog.dismiss
import java.util.*


/**
 * Created by misa on 3/29/18.
 */
class CommentsFragment : Fragment() {
    lateinit var comments:ArrayList<Comments>
    lateinit var rv:RecyclerView
     var id_image=R.drawable.k

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


         comments = this.arguments?.getSerializable("comment")as ArrayList<Comments>
        val rootView = inflater.inflate(R.layout.fragment_comments, container, false)
         rv= rootView.findViewById<RecyclerView>(R.id.recycleViewComments)
        rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)



        var adapter = CommentAdapter(comments)
        rv.adapter = adapter

        // Code pour ajouter un commentaire
        val editText = rootView.findViewById(R.id.addComment) as EditText
        editText.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){


                false
            } else {

                //addComment(v.text.toString(),id_image)
                v.text=""


                true
            }
        }



        return rootView
    }

    companion object {

        fun newInstance( comment : ArrayList<Comments>): CommentsFragment {

            val args = Bundle()

            args.putSerializable("comment", comment)

            val fragment = CommentsFragment()
            fragment.arguments = args
            return fragment
        }
    }

   /* fun addComment(v:String, id:Int) {

        comments.add(Comments("newUser",v,id))
        var adapter = CommentAdapter(comments)
        rv.adapter = adapter
        rv.invalidate()
    }*/
    fun random_id():Int{
        var id=R.drawable.e
        val num=(0..12).random()
        when(num){
            0-> id=R.drawable.a
            1-> id=R.drawable.b
            2-> id=R.drawable.c
            3-> id=R.drawable.d
            4-> id=R.drawable.e
            5-> id=R.drawable.f
            6-> id=R.drawable.h
            7-> id=R.drawable.i
            8-> id=R.drawable.j
            9-> id=R.drawable.k
            10-> id=R.drawable.l
            11-> id=R.drawable.g
        }

        return id;
    }
    fun ClosedRange<Int>.random() =
            Random().nextInt(endInclusive - start) +  start
}