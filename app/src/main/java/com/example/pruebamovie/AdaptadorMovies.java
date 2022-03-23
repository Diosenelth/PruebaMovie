package com.example.pruebamovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdaptadorMovies extends RecyclerView.Adapter<AdaptadorMovies.ViewHolder> {
    List<Movie> movies;
    View view;
    public AdaptadorMovies(List<Movie> movies, View view) {
        this.movies = movies;
        this.view=view;
    }

    @NonNull
    @Override
    public AdaptadorMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView tv;
        final ImageView imageView;
        final ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.imageView);
            constraintLayout=itemView.findViewById(R.id.fondo);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie=movies.get(position);
        holder.tv.setText(movie.getOriginal_title());
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path())
                .centerInside()
                //.fit()
                //.resize(140,140)
                //.placeholder(R.drawable.)
                .into(holder.imageView);
        holder.constraintLayout.setOnClickListener(view1 ->
                Toast.makeText(view.getContext(),
                        movie.getOriginal_title(),
                        Toast.LENGTH_SHORT).show());
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
