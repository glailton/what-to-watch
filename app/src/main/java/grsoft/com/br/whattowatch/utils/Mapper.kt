package grsoft.com.br.whattowatch.utils

import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.response.series.Result

fun mapper(results: List<Result>): List<TVShow> {
    var tvShows: MutableList<TVShow> = mutableListOf()
    results.forEach { result ->
        val tvShow = TVShow(result.id, result.name, result.firstAirDate, result.genreIds,
            result.originCountry, result.originalLanguage, result.originalName, result.overview,
            result.popularity, result.posterPath, result.voteAverage, result.voteCount,
            result.backdropPath)
        tvShows.add(tvShow)
    }

    return tvShows
}