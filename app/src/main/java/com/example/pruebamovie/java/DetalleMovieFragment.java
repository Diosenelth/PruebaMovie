package com.example.pruebamovie.java;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.pruebamovie.R;
import com.example.pruebamovie.databinding.FragmentDetalleMovieBinding;
import com.example.pruebamovie.kotlin.Moviedetail;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleMovieFragment extends Fragment {
    private Movie movie;
    FragmentDetalleMovieBinding binding;
    MovieService movieService;

    public DetalleMovieFragment() {
        // Required empty public constructor
    }

    public DetalleMovieFragment(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detalle_movie, container, false);
        binding = FragmentDetalleMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieService = new MovieService();
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getBackdrop_path())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(binding.appBarImage);
        binding.detalle.Titulo.setText(movie.getTitle());
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(binding.detalle.imagenPortada);
        binding.detalle.info.setText(movie.getOverview());
        new Thread(()->{
            movieService.getRutas().getMovie(movie.id).enqueue(new Callback<Moviedetail>() {
                @Override
                public void onResponse(Call<Moviedetail> call, Response<Moviedetail> response) {
                    if (response.isSuccessful() && response.body()!=null){
                        Moviedetail movie= response.body();
                        requireActivity().runOnUiThread(()->{
                            binding.detalle.Url.setText(movie.getHomepage());
                            binding.detalle.Url.setVisibility(View.VISIBLE);
                            binding.detalle.fecha.setText(movie.getRelease_date());
                            binding.detalle.fecha.setVisibility(View.VISIBLE);
                            binding.detalle.Url.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                            binding.detalle.Url.setOnClickListener((a)->{
                                Intent intent= new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(movie.getHomepage()));
                                startActivity(intent);
                            });
                        });
                    }
                }

                @Override
                public void onFailure(Call<Moviedetail> call, Throwable t) {

                }
            });
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }
}