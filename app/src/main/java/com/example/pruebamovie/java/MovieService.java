package com.example.pruebamovie.java;


import com.example.pruebamovie.kotlin.Moviedetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MovieService {
    private final ApiClient apiClient;

    public MovieService() {
        this.apiClient = new ApiClient();
    }

    public Rutas getRutas() {
        return apiClient.getRetrofit().create(Rutas.class);
    }

    public interface Rutas {
        @GET("movie/popular")
        Call<MoviesRes> getPopularMovies(@Query("page") int page);

        @GET("movie/{id}")
        Call<Moviedetail> getMovie(@Path("id") String id);

        @GET("search/company?")
        Call<MoviesRes> search(@Query("query") int page);

        @GET("movie/top_rated")
        Call<MoviesRes> getTopRatedMovies(@Query("page") int page);

        @GET("movie/now_playing")
        Call<MoviesRes> getNowPlayingMovies(@Query("page") int page);
    }
}
