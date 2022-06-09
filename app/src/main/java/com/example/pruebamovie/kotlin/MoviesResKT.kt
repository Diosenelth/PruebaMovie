package com.example.pruebamovie.kotlin

data class MoviesResKT(
    var page: String, var total_results: String,
    var total_pages: String, var results: List<MovieKt>
)