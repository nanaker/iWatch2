package com.example.misa.iwatch.ui.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.misa.iwatch.entity.Personnes

class ActorsViewModel:ViewModel(){
    val actors:MutableLiveData<List<Personnes>> = MutableLiveData()

    fun getActors():LiveData<List<Personnes>>{
        if(actors.value?.size == 0){
            //load data
        }
        return actors
    }

}