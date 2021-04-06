package com.vedom.cinema.fragments

import android.os.Bundle
import android.util.Log
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
    }

    override fun onDetach() {
        progressBar = null
        moviesListRv?.adapter = null
        moviesListRv = null
        progressBar = null
        super.onDetach()

    }

    private fun updateData(data: List<Movie>) {
        adapter.bindMovies(newMovies = data)
        adapter.notifyDataSetChanged()
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
        progressBar = null
        moviesListRv?.adapter = null
        moviesListRv = null
        Log.e("Here", "Show destroy")
        super.onDestroyView()

    }

    companion object{
        fun newInstance(): FragmentMoviesList {
            val fragment = FragmentMoviesList()
            return fragment
        }

    }
}