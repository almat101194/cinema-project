package com.vedom.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE1
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE10
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE11
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE12
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE13
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE14
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE15
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE16
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE2
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE3
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE4
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE5
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE6
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE7
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE8
import com.vedom.cinema.FragmentMoviesList.Companion.MOVIE9
import com.vedom.cinema.data.Actor
import com.vedom.cinema.data.Movie
import com.vedom.cinema.data.loadMovies
import com.vedom.cinema.utils.imageOption
import kotlinx.android.synthetic.main.activity_movie_details.view.*
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

        setUpUI(view)
    }

    private fun setUpUI(view: View) {
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
//        var actor1: Actor = Actor(1, arguments?.getString(MOVIE9), arguments?.getInt(MOVIE12))
//        var actor2: Actor = Actor(1, arguments?.getString(MOVIE10), arguments?.getInt(MOVIE13))
//        var actor3: Actor = Actor(1, arguments?.getString(MOVIE11), arguments?.getInt(MOVIE14))
//        var actor4: Actor = Actor(1, arguments?.getString(MOVIE16), arguments?.getInt(MOVIE15))
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val args = Bundle()
            args.putParcelable(SAVE_MOVIE_DATA_KEY, movie)
//            args.putString(MOVIE1, movie.storyLine)
//            args.putString(MOVIE2, movie.title)
//            args.putInt(MOVIE3, movie.pg)
//            args.putFloat(MOVIE4, movie.rating)
//            args.putInt(MOVIE5, movie.reViews)
//            args.putString(MOVIE6, movie.tags[0])
//            args.putString(MOVIE7, movie.tags[1])
//            args.putString(MOVIE8, movie.tags[2])
//            args.putString(MOVIE9, movie.actorInfoList?.get(0)?.name)
//            args.putString(MOVIE10, movie.actorInfoList?.get(1)?.name)
//            args.putString(MOVIE11, movie.actorInfoList?.get(2)?.name)
//            args.putString(MOVIE16, movie.actorInfoList?.get(3)?.name)
//            movie.actorInfoList?.get(0)?.image?.let { args.putInt(MOVIE12, it) }
//            movie.actorInfoList?.get(1)?.image?.let { args.putInt(MOVIE13, it) }
//            movie.actorInfoList?.get(2)?.image?.let { args.putInt(MOVIE14, it) }
//            movie.actorInfoList?.get(3)?.image?.let { args.putInt(MOVIE15, it) }
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
        private const val SAVE_MOVIE_DATA_KEY = "SAVE_MOVIE_DATA_KEY"
    }

}