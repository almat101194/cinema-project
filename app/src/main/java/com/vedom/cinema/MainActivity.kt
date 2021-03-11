package com.vedom.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveToFragmentMoviesList(savedInstanceState)
    }

    private fun moveToFragmentMoviesList(savedInstanceState: Bundle?) {
        fragmentMoviesList = FragmentMoviesList.newInstance()
        if (savedInstanceState == null) {
            fragmentMoviesList?.apply {
                supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.main_container, this, FRAGMENT_MOVIE_LIST_TAG)
                        .commit()
            }
        }else {
            fragmentMoviesList =
                    supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_LIST_TAG) as? FragmentMoviesList
        }
    }

    companion object {
        const val FRAGMENT_MOVIE_LIST_TAG = "Fragment Movie Lists"
    }
}