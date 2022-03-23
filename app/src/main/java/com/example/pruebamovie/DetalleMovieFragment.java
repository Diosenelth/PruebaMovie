package com.example.pruebamovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.pruebamovie.databinding.FragmentDetalleMovieBinding;
import com.example.pruebamovie.databinding.FragmentMoviesBinding;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleMovieFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Movie movie;
    FragmentDetalleMovieBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleMovieFragment() {
        // Required empty public constructor
    }

    public DetalleMovieFragment(Movie movie) {
        this.movie = movie;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleMovieFragment newInstance(String param1, String param2) {
        DetalleMovieFragment fragment = new DetalleMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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