package com.example.pruebamovie.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamovie.databinding.FragmentMoviesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesFragmentKT : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    var movies = mutableListOf<MovieKt>()
    var response: MoviesResKT? = null
    var manager: LinearLayoutManager? = null

    private lateinit var adaptadorMoviesKT: AdaptadorMoviesKT
    private lateinit var movieService: MovieServiceKT
    var page = 0
    var totalpages = 0
    var isScrolling = false
    var currentItems = 0
    var totalItems = 0
    var scrolledOutItems = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager = GridLayoutManager(context, 2)
        init()
    }

    private fun init() {
        adaptadorMoviesKT = AdaptadorMoviesKT(movies, requireView())
        movieService = MovieServiceKT()
        binding.rv1.setHasFixedSize(true)
        binding.rv1.layoutManager = manager
        binding.rv1.adapter = adaptadorMoviesKT
        binding.rv1.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = manager!!.childCount
                totalItems = manager!!.itemCount
                scrolledOutItems = manager!!.findFirstVisibleItemPosition()
                if (isScrolling && (currentItems + scrolledOutItems == totalItems)) {
                    isScrolling = false
                    if (page < totalpages) {
                        page++
                        cargar()
                    } else {
                        Toast.makeText(context, "No hay mas peliculas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun cargar() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = movieService.getRutas().getPopularMovies("$page").execute()
                if (call.isSuccessful) {
                    response = call.body()
                    movies.addAll(response!!.results)
                    requireActivity().runOnUiThread() {
                        adaptadorMoviesKT.notifyItemInserted(movies.size)
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

}