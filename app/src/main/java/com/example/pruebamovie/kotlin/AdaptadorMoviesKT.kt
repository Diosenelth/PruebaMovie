package com.example.pruebamovie.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamovie.R

class AdaptadorMoviesKT(var movies: List<MovieKt>, var view:View):RecyclerView.Adapter<AdaptadorMoviesKT.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv=itemView.findViewById<View>(R.id.textView) as TextView
        val imageView=itemView.findViewById<View>(R.id.imageView) as ImageView
        val cardView=itemView.findViewById<View>(R.id.card_view) as CardView
        val star = itemView.findViewById<View>(R.id.progressBar) as ProgressBar
        val proText = itemView.findViewById<View>(R.id.progressText) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie=movies[position]
        val voto =movie.vote_average * 10.0
        val voString = movie.vote_average.toString()
        val img="https://image.tmdb.org/t/p/w500"+movie.poster_path
        holder.tv.text=movie.title
        Glide.with(view.context)
            .load(img)
            .centerInside()
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(holder.imageView)
        holder.star.progress=voto.toInt()
        holder.proText.text = voString
        holder.cardView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val details= DetalleMovieFragmentKT()
            details.movie=movie
            activity
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, details)
                .addToBackStack("principal")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}