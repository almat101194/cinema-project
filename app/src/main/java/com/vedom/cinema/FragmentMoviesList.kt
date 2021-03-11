package com.vedom.cinema

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList : Fragment() {

    private var moviesListRv: RecyclerView? = null
    private val clickListener = object : ClickedListener {
        override fun onClick(movie: Movie) {
            moveToFragmentMoviesDetails(movie)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI(view)
    }

    private fun moveToFragmentMoviesDetails(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, FragmentMoviesDetails.newInstance(movie))
            .commit()
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDetach() {
        moviesListRv = null
        super.onDetach()
    }

    private fun updateData() {
        (moviesListRv?.adapter as? MoviesListAdapter)?.apply {
            bindMovies(MovieDataSource().getMovies())
        }
    }

    private fun setUpUI(view: View) {
        moviesListRv = view.findViewById(R.id.id_rv_movies_list)
        moviesListRv?.adapter = MoviesListAdapter(clickListener)
        moviesListRv?.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    companion object{
        const val MOVIE1 = "MOVIE1"
        const val MOVIE2 = "MOVIE2"
        const val MOVIE3 = "MOVIE3"
        const val MOVIE4 = "MOVIE4"
        const val MOVIE5 = "MOVIE5"
        const val MOVIE6 = "MOVIE6"
        const val MOVIE7 = "MOVIE7"
        const val MOVIE8 = "MOVIE8"
        const val MOVIE9 = "MOVIE9"
        const val MOVIE10 = "MOVIE10"
        const val MOVIE11 = "MOVIE11"
        const val MOVIE12 = "MOVIE12"
        const val MOVIE13 = "MOVIE13"
        const val MOVIE14 = "MOVIE14"
        const val MOVIE15 = "MOVIE15"
        const val MOVIE16 = "MOVIE16"

//        const val FRAGMENT_MOVIES_DETAILS = "Fragment Movies Details"

        fun newInstance(): FragmentMoviesList {
            val fragment = FragmentMoviesList()
            return fragment
        }

    }
}