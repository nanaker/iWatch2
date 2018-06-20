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
import com.example.misa.iwatch.ui.adapters.CommentAdapter
import com.example.misa.iwatch.entity.Comments

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.example.misa.iwatch.Repository.IRepository
import com.example.misa.iwatch.Repository.Movies.MovieDetailRepository
import com.example.misa.iwatch.Repository.sieries.SerieDetailRepository
import com.example.misa.iwatch.entity.Movie
import com.example.misa.iwatch.entity.ReviewResponse
import com.example.misa.iwatch.ui.ViewModels.MoviesDetailViewModel
import com.example.misa.iwatch.ui.ViewModels.SeriesDetailViewModel
import com.example.misa.iwatch.utils.ServiceLocator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_comments.*
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


         var id = this.arguments?.getInt("id_movie")!!
        var type= this.arguments?.getInt("type")!!

        when(type){
            1-> {
                val repo = ServiceLocator.instance()
                        .getRepository(IRepository.Type.DETAILMOVIE) as MovieDetailRepository

                val DetailFilmModel =  MoviesDetailViewModel(repo)
                DetailFilmModel.getreview(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)

            }
            2-> {
                val repo = ServiceLocator.instance()
                        .getRepository(IRepository.Type.DETAILSERIE) as SerieDetailRepository

                val DetailSerieModel =  SeriesDetailViewModel(repo)
                DetailSerieModel.getreview(id)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError)

            }
            //3->
        }

        println("id movie comment")


        val rootView = inflater.inflate(R.layout.fragment_comments, container, false)
        rv= rootView.findViewById<RecyclerView>(R.id.recycleViewComments)



        return rootView
    }

    private fun handleResponse(review: ReviewResponse) {
        this.comments=review.comments
        println("comment size "+comments.size)

        this.rv.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        var adapter = CommentAdapter(this.comments)
        this.rv.adapter = adapter
        this.rv.invalidate();


        //val editText = rootView.findViewById(R.id.addComment) as EditText

        addComment.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){


                false
            } else {

                //addComment(v.text.toString(),id_image)
                v.text=""


                true
            }
        }

    }

    private fun handleError(error: Throwable) { Log.d("error review load ", error.localizedMessage) }



    companion object {

        fun newInstance(id_movie: Int,type:Int): CommentsFragment {

            val args = Bundle()

            args.putInt("id_movie", id_movie)
            args.putInt("type", type)

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