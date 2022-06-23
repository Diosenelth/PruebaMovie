package com.example.pruebamovie.kotlin

data class Moviedetail (
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_Count: Long
)

data class BelongsToCollection (
    val id: Long,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)

data class Genre (
    val id: Long,
    val name: String
)

data class ProductionCompany (
    val id: Long,
    val logo_Path: String,
    val name: String,
    val origin_Country: String
)

data class ProductionCountry (
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage (
    val english_Name: String,
    val iso_639_1: String,
    val name: String
)
