package com.vedom.cinema.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vedom.cinema.adapters.ActorsListAdapter
import com.vedom.cinema.R
import com.vedom.cinema.factory.ViewModelFactory
import com.vedom.cinema.models.data.Actor
import com.vedom.cinema.models.data.Movie
import com.vedom.cinema.utils.imageOption
import com.vedom.cinema.viewmodeles.MoviesDetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class FragmentMoviesDetails : Fragment() {

    private var actorsListRv: RecyclerView? = null
    private lateinit var backCoverImage: ImageView
    private var tvPg: TextView? = null
    private var tvMovieName: TextView? = null
    private var tvTags: TextView? = null
    private var rbMovieDetails: RatingBar? = null
    private var tvReviews: TextView? = null
    private var tvStoryLine: TextView? = null
    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null
    private var progressBar: ProgressBar? = null
    private val viewModel: MoviesDetailsViewModel by viewModels {ViewModelFactory(context = requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, this::setLoading)
        setUpUI(view)
    }

    private fun setLoading(state: Boolean) {
        progressBar?.isVisible = state
    }

    private fun setUpUI(view: View) {
        progressBar = view.findViewById(R.id.pb_movies_details)
        actorsListRv = view.findViewById(R.id.id_rv_actors)
        actorsListRv?.adapter = ActorsListAdapter()
        actorsListRv?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        actorsListRv?.setHasFixedSize(true)

        var movies = arguments?.get(SAVE_MOVIE_DATA_KEY) as Movie

        backCoverImage= view.findViewById(R.id.iv_name_movie0)
        setBackPoster(movies.backdrop, backCoverImage)

        tvPg = view.findViewById(R.id.tv_pg)
        tvPg?.text = "${movies.minimumAge}+"

        tvMovieName = view.findViewById((R.id.tv_movie_name))
        tvMovieName?.text = (arguments?.get(SAVE_MOVIE_DATA_KEY) as Movie).title

        tvTags = view.findViewById(R.id.tv_movie_tag)
        tvTags?.text = "${movies.genres.joinToString(separator = ", ") { it.name }}"

        rbMovieDetails = view.findViewById(R.id.rb_of_movie)
        rbMovieDetails?.rating = movies.ratings/2


        tvReviews = view.findViewById(R.id.tv_review)
        tvReviews?.text = "${movies.numberOfRatings} REVIEWS"

        tvStoryLine = view.findViewById(R.id.tv_movie_description)
        tvStoryLine?.text = movies.overview
    }

    private fun setBackPoster(str: String, imageView: ImageView) {
        Glide.with(requireActivity())
                .load(str)
                .apply(imageOption)
                .into(imageView)
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    override fun onDetach() {
        tvPg = null
        tvMovieName = null
        tvTags = null
        rbMovieDetails = null
        tvStoryLine = null
        tvReviews = null
        progressBar = null
        actorsListRv = null
        super.onDetach()
    }

    private fun updateData() {
        var actorsList: List<Actor> = (arguments?.get(SAVE_MOVIE_DATA_KEY) as Movie).actors
        job = scope.launch {

            (actorsListRv?.adapter as? ActorsListAdapter)?.apply {
                bindActors(actorsList)
            }

        }
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val args = Bundle()
            args.putParcelable(SAVE_MOVIE_DATA_KEY, movie)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
        private const val SAVE_MOVIE_DATA_KEY = "SAVE_MOVIE_DATA_KEY"
    }

}