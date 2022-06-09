package com.example.pruebamovie.java;

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

import java.util.Objects;

public class DetalleMovieFragment extends Fragment {
    private Movie movie;
    FragmentDetalleMovieBinding binding;

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();
    }
}