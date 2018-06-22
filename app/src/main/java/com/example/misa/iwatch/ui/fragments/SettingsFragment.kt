package com.example.misa.iwatch.ui.fragments

import android.os.Bundle
import android.support.v14.preference.PreferenceFragment
import com.example.misa.iwatch.R

class SettingsFragment: PreferenceFragment(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.app_prefrences)
    }

}