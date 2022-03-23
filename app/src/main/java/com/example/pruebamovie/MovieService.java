package com.example.pruebamovie;


import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MovieService {
    private ApiClient apiClient;

    public MovieService() {
        this.apiClient = new ApiClient();
    }
    public Rutas getRutas(){
        return apiClient.getRetrofit().create(Rutas.class);
    }

    public interface Rutas{
        @GET("movie/popular")
        Call<MoviesRes> getPopularMovies(@Query("page") int page);

        @GET("search/company?")
        Call<MoviesRes> search(@Query("query") int page);


        @GET("movie/top_rated")
        Call<MoviesRes> getTopRatedMovies(@Query("page") int page);

        @GET("movie/now_playing")
        Call<MoviesRes> getNowPlayingMovies(@Query("page") int page);

        // Instead of using 4 separate requests we use append_to_response
        // to eliminate duplicate requests and save network bandwidth
        // this request return full movie details, trailers, reviews and cast
        //@GET("movie/{id}?append_to_response=videos,credits,reviews")
        //LiveData<ApiResponse<String>> getMovieDetails(@Path("id") long id);
    }
}
