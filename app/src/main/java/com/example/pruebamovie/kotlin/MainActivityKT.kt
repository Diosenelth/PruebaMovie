package com.example.pruebamovie.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.example.pruebamovie.R
import com.example.pruebamovie.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityKT : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var page:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargar(page)
    }

    private fun cargar(page: Int) {
        val movieServiceKT= MovieServiceKT()
        binding.lottie.visibility= View.VISIBLE
        binding.lottie.setAnimation(R.raw.laa)
        binding.lottie.playAnimation()
        binding.fragmentContainerView.visibility=View.INVISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =movieServiceKT.getRutas().getPopularMovies("$page").execute()
                val body=response.body()!!
                runOnUiThread(){
                    val moviesFragmentKT= MoviesFragmentKT()
                    moviesFragmentKT.movies= body.results as MutableList<MovieKt>
                    moviesFragmentKT.page=1
                    moviesFragmentKT.totalpages= body.total_pages.toInt()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainerView, moviesFragmentKT)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                    binding.lottie.visibility= View.INVISIBLE
                    binding.fragmentContainerView.visibility=View.VISIBLE
                }
            }catch (e:Exception){
                runOnUiThread() {
                    binding.lottie.setAnimation(R.raw.error)
                    binding.lottie.playAnimation()
                }
            }
        }
    }
}