package com.example.pruebamovie;

import android.os.Handler;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresentador {
    IPresentador vista;
    MovieService movieService;
    int page, error;

    public MainPresentador(IPresentador vista, int page) {
        this.vista = vista;
        this.page = page;
        error = 0;
    }

    public void cargar() {
        movieService = new MovieService();
        movieService.getRutas().getPopularMovies(page).enqueue(new Callback<MoviesRes>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRes> call, @NonNull Response<MoviesRes> response) {
                if (response.isSuccessful()) {
                    vista.cargarMovies(response.body());
                } else {
                    vista.error();
                    vista.mostrarAlerta("No se recibio ningun dato");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRes> call, @NonNull Throwable t) {
                if (error < 3) {
                    error++;
                    vista.mostrarAlerta(t.getMessage());
                    vista.error();
                    new Handler().postDelayed(() -> {
                        vista.mostrarAlerta("Reintentando");
                        cargar();
                    }, 3000);
                } else {
                    vista.mostrarAlerta(t.getMessage());
                    vista.error();
                    vista.mostrarAlerta("Verifique su conexion a internet");
                }
            }
        });
    }
}
