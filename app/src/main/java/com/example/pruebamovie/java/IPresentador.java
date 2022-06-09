package com.example.pruebamovie.java;

import com.example.pruebamovie.java.MoviesRes;

public interface IPresentador {
    void cargar(int page);
    void mostrarAlerta(String mensaje);
    void cargarMovies(MoviesRes moviesRes);
    void error();
}
