package grsoft.com.br.whattowatch.utils

import grsoft.com.br.whattowatch.data.entities.Genre
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.response.series.Result

fun mapperResultToTvShow(results: List<Result>): List<TVShow> {
    var tvShows: MutableList<TVShow> = mutableListOf()
    results.forEach { result ->
        val backdropPath = if (result.backdropPath.isNullOrBlank()) "" else result.backdropPath
        val posterPath = if (result.posterPath.isNullOrBlank()) "" else result.posterPath
        val tvShow = TVShow(result.id, result.name, result.firstAirDate, result.genreIds,
            result.originCountry, result.originalLanguage, result.originalName, result.overview,
            result.popularity, posterPath, result.voteAverage, result.voteCount,
            backdropPath)
        tvShows.add(tvShow)
    }

    return tvShows
}

fun mapperResultToGenre(results: List<grsoft.com.br.whattowatch.data.response.genre.Genre>): List<Genre> {
    var genres: MutableList<Genre> = mutableListOf()
    results.forEach { result ->
        val genre = Genre(result.id, result.name)
        genres.add(genre)
    }

    return genres
}