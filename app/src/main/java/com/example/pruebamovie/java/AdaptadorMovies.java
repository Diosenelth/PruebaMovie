package com.example.pruebamovie.java;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebamovie.R;

import java.util.List;

public class AdaptadorMovies extends RecyclerView.Adapter<AdaptadorMovies.ViewHolder> {
    List<Movie> movies;
    View view;

    public AdaptadorMovies(List<Movie> movies, View view) {
        this.movies = movies;
        this.view = view;
    }

    @NonNull
    @Override
    public AdaptadorMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tv;
        final ImageView imageView;
        final CardView cardView;
        final ProgressBar star;
        final TextView proText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.card_view);
            star = itemView.findViewById(R.id.progressBar);
            proText = itemView.findViewById(R.id.progressText);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        double votoDouble= movie.vote_average * 10.0;
        int voto = (int) votoDouble;
        holder.tv.setText(movie.getTitle());
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .centerInside()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.imageView);
        holder.star.setProgress(voto);
        String votoAver=movie.vote_average.toString();
        holder.proText.setText(votoAver);
        holder.cardView.setOnClickListener(view1 -> {
            AppCompatActivity activity = (AppCompatActivity) view1.getContext();
            DetalleMovieFragment detalleMovieFragment = new DetalleMovieFragment(movie);
            activity
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, detalleMovieFragment)
                    .addToBackStack("principal")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        });
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.with(view.getContext())
                .clear(holder.imageView);
    }
}
