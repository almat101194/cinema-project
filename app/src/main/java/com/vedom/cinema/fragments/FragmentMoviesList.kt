package com.vedom.cinema.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vedom.cinema.interfaces.ClickedListener
import com.vedom.cinema.adapters.MoviesListAdapter
import com.vedom.cinema.R
import com.vedom.cinema.factory.ViewModelFactory
import com.vedom.cinema.models.data.Movie
import com.vedom.cinema.models.data.loadMovies
import com.vedom.cinema.viewmodeles.MoviesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment() {

    private var moviesListRv: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private val viewModel: MoviesListViewModel by viewModels {ViewModelFactory(context = requireContext())}

    private val clickListener = object : ClickedListener {
        override fun onClick(movie: Movie) {
            moveToFragmentMoviesDetails(movie)
        }
    }
    private var adapter = MoviesListAdapter(clickListener)
    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null

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
        viewModel.movieList.observe(viewLifecycleOwner, this::updateData)
        viewModel.state.observe(viewLifecycleOwner, this::setStateLoading)
        viewModel.loadMovies()
    }

    private fun setStateLoading(state: Boolean) {
        progressBar?.isVisible = state
    }

    private fun moveToFragmentMoviesDetails(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_container, FragmentMoviesDetails.newInstance(movie))
            .commit()
    }

    override fun onStart() {
        super.onStart()
//        updateData()
    }

    override fun onDetach() {
        moviesListRv = null
        progressBar = null
        super.onDetach()
    }

    private fun updateData(data: List<Movie>) {
        adapter.bindMovies(newMovies = data)
        adapter.notifyDataSetChanged()
//        job = scope.launch {
//
//            (moviesListRv?.adapter as? MoviesListAdapter)?.apply {
//                bindMovies(loadMovies(requireContext()))
//            }
//
//        }
//        (moviesListRv?.adapter as? MoviesListAdapter)?.apply {
//            bindMovies(MovieDataSource().getMovies())
//        }
    }

    private fun setUpUI(view: View) {
        progressBar = view.findViewById(R.id.pb_movies_list)
        moviesListRv = view.findViewById(R.id.id_rv_movies_list)
        moviesListRv?.adapter = adapter
        moviesListRv?.layoutManager = GridLayoutManager(requireContext(), 2)
        moviesListRv?.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        job?.cancel()
        super.onDestroyView()
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