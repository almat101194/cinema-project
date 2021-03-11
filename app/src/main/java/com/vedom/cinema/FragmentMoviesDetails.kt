package com.vedom.cinema

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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


class FragmentMoviesDetails : Fragment() {

    private var actorsListRv: RecyclerView? = null
    private var tvPg: TextView? = null
    private var tvMovieName: TextView? = null
    private var tvTags: TextView? = null
    private var rbMovieDetails: RatingBar? = null
    private var tvReviews: TextView? = null
    private var tvStoryLine: TextView? = null

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

        tvPg = view.findViewById(R.id.tv_pg)
        tvPg?.text = "${arguments?.getInt(MOVIE3)}+"

        tvMovieName = view.findViewById((R.id.tv_movie_name))
        tvMovieName?.text = arguments?.getString(MOVIE2)

        tvTags = view.findViewById(R.id.tv_movie_tag)
        tvTags?.text = "${arguments?.getString(MOVIE6)}, ${arguments?.getString(MOVIE7)}, ${arguments?.getString(MOVIE8)}"

        rbMovieDetails = view.findViewById(R.id.rb_of_movie)
        rbMovieDetails?.rating = arguments?.getFloat(MOVIE4)!!


        tvReviews = view.findViewById(R.id.tv_review)
        tvReviews?.text = "${arguments?.getInt(MOVIE5)} REVIEWS"

        tvStoryLine = view.findViewById(R.id.tv_movie_description)
        tvStoryLine?.text = arguments?.getString(MOVIE1)
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
        var actor1: Actor = Actor(1, arguments?.getString(MOVIE9), arguments?.getInt(MOVIE12))
        var actor2: Actor = Actor(1, arguments?.getString(MOVIE10), arguments?.getInt(MOVIE13))
        var actor3: Actor = Actor(1, arguments?.getString(MOVIE11), arguments?.getInt(MOVIE14))
        var actor4: Actor = Actor(1, arguments?.getString(MOVIE16), arguments?.getInt(MOVIE15))
        var actorsList: List<Actor> = listOf(actor1, actor2, actor3, actor4)
        (actorsListRv?.adapter as? ActorsListAdapter)?.apply {
            bindActors(actorsList)
        }
    }

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val args = Bundle()
            args.putString(MOVIE1, movie.storyLine)
            args.putString(MOVIE2, movie.title)
            args.putInt(MOVIE3, movie.pg)
            args.putFloat(MOVIE4, movie.rating)
            args.putInt(MOVIE5, movie.reViews)
            args.putString(MOVIE6, movie.tags[0])
            args.putString(MOVIE7, movie.tags[1])
            args.putString(MOVIE8, movie.tags[2])
            args.putString(MOVIE9, movie.actorInfoList?.get(0)?.name)
            args.putString(MOVIE10, movie.actorInfoList?.get(1)?.name)
            args.putString(MOVIE11, movie.actorInfoList?.get(2)?.name)
            args.putString(MOVIE16, movie.actorInfoList?.get(3)?.name)
            movie.actorInfoList?.get(0)?.image?.let { args.putInt(MOVIE12, it) }
            movie.actorInfoList?.get(1)?.image?.let { args.putInt(MOVIE13, it) }
            movie.actorInfoList?.get(2)?.image?.let { args.putInt(MOVIE14, it) }
            movie.actorInfoList?.get(3)?.image?.let { args.putInt(MOVIE15, it) }
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }

}