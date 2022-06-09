package com.example.pruebamovie.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebamovie.databinding.FragmentMoviesBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {
    FragmentMoviesBinding binding;
    RecyclerView recyclerView;
    List<Movie> movies;
    MoviesRes response;
    LinearLayoutManager manager;
    MovieService movieService;
    AdaptadorMovies adaptadorMovies;
    private int page;
    boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    IPresentador vista;
    public MoviesFragment() {
        // Required empty public constructor
    }

    public MoviesFragment(MoviesRes response, int page, IPresentador vista) {
        this.movies = response.getResults();
        this.response = response;
        this.page = page;
        this.vista = vista;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.rv1;
        init();
    }

    public void init() {
        movieService = new MovieService();
        manager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        adaptadorMovies = new AdaptadorMovies(movies, getView());
        recyclerView.setAdapter(adaptadorMovies);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    if (page < Integer.parseInt(response.getTotal_pages())) {
                        page++;
                        cargar();
                    } else {
                        Toast.makeText(getContext(), "No hay mas peliculas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void cargar() {
        new Thread(() -> movieService.getRutas().getPopularMovies(page).enqueue(new Callback<MoviesRes>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRes> call, @NonNull Response<MoviesRes> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    movies.addAll(response.body().getResults());
                    requireActivity().runOnUiThread(()->adaptadorMovies.notifyItemInserted(movies.size()));
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRes> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        })).start();
    }
}