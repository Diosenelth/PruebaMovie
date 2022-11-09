package com.example.pruebamovie.java;

public interface IPresentador {
    void cargar(int page);

    void mostrarAlerta(String mensaje);

    void cargarMovies(MoviesRes moviesRes);

    void error();
}
