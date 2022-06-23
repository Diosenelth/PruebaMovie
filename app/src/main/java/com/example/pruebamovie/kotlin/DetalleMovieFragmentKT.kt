package com.example.pruebamovie.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pruebamovie.R
import com.example.pruebamovie.databinding.FragmentDetalleMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalleMovieFragmentKT : Fragment() {
    private var _binding: FragmentDetalleMovieBinding? = null
    private val binding get() = _binding!!

    var movie: MovieKt?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        val movieServiceKT= MovieServiceKT()
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/w500"+movie!!.backdrop_path)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(binding.appBarImage)
        binding.detalle.Titulo.text=movie!!.title
        Glide.with(view.context)
            .load("https://image.tmdb.org/t/p/w500"+movie!!.poster_path)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(binding.detalle.imagenPortada)
        binding.detalle.info.text=movie!!.overview
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieServiceKT.getRutas().getMovie(movie!!.id.toString()).execute()
                if (response.isSuccessful) {
                    val body = response.body()!!
                    requireActivity().runOnUiThread {
                        binding.detalle.Url.text = body.homepage
                        binding.detalle.fecha.text = body.release_date
                        binding.detalle.fecha.visibility = View.VISIBLE
                        binding.detalle.Url.visibility = View.VISIBLE
                    }
                }
            }catch (e: Exception){
                print(e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }
}