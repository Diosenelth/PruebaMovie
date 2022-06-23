package com.example.pruebamovie.kotlin

data class MovieKt(
    val adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Long>,
    val id: Long,
    var original_language: String,
    var original_title: String,
    var overview: String,
    val popularity: Double,
    var poster_path: String,
    var release_date: String,
    var title: String,
    val video: Boolean,
    val vote_average: Double,
    var vote_count: String
)