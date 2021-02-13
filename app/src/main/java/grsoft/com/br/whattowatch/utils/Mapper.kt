package grsoft.com.br.whattowatch.utils

import grsoft.com.br.whattowatch.data.entities.*
import grsoft.com.br.whattowatch.data.response.details.TVShowDetailsResponse
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

fun mapperResultToDetails(result: TVShowDetailsResponse): Details {
//    val createdBy: MutableList<Int> = mutableListOf()
    val genres: MutableList<Genre> = mutableListOf()
    val networks: MutableList<Int> = mutableListOf()
    val productionCompanies: MutableList<Int> = mutableListOf()
    val productionCountries: MutableList<String> = mutableListOf()
    val seasons: MutableList<Int> = mutableListOf()
    val spokenLanguages: MutableList<String> = mutableListOf()
//    result.createdBy.forEach {
//       val createdByEntity = it.id
//        createdBy.add(createdByEntity)
//    }
    result.genres.forEach {
        val genre = Genre(it.id, it.name)
        genres.add(genre)
    }
    result.networks.forEach {
        val networkId = it.id
        networks.add(networkId)
    }
    result.productionCompanies.forEach {
        val productionCompany = it.id
        productionCompanies.add(productionCompany)
    }
    result.productionCountries.forEach {
        val productionCountry = it.name
        productionCountries.add(productionCountry)
    }
    result.seasons.forEach {
        val season = it.id
        seasons.add(season)
    }
    result.spokenLanguages.forEach {
        val spokenLanguage = it.name
        spokenLanguages.add(spokenLanguage)
    }

    return Details(
        result.id, result.backdropPath, result.createdBy.getOrNull(0)?.id, result.episodeRunTime, result.firstAirDate,
        genres, result.homepage, result.inProduction, result.languages, result.lastAirDate,
        result.lastEpisodeToAir.id, result.name, result.nextEpisodeToAir?.let { it.id },
        networks, result.numberOfEpisodes, result.numberOfSeasons, result.originCountry,
        result.originalLanguage, result.originalName, result.overview, result.popularity,
        result.posterPath, productionCompanies, productionCountries, seasons, spokenLanguages,
        result.status, result.tagline, result.type, result.voteAverage, result.voteCount)
}