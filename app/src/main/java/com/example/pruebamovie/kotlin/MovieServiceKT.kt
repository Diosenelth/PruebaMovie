package com.example.pruebamovie.kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class MovieServiceKT {

    private val apiClienteKT: ApiClienteKT = ApiClienteKT()

    fun getRutas(): Rutas {
        return apiClienteKT.getApiService().create(Rutas::class.java)
    }

    interface Rutas {
        @GET("/3/movie/popular")
        fun getPopularMovies(@Query("page") url: String): Call<MoviesResKT>
    }
}