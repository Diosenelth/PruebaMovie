package com.example.pruebamovie.kotlin

data class MoviesResKT(
    var page: String,
    var total_results: Int,
    var total_pages: Int,
    var results: List<MovieKt>
)