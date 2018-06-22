package com.example.misa.iwatch.notification

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import android.R.attr.tag



class LatestMovieJobCreator : JobCreator {
    override fun create(tag: String): Job? {
        when (tag) {
            LatestMovieJob.TAG-> return LatestMovieJob()
            else -> return null
        }

    }
}