package grsoft.com.br.whattowatch.data

import grsoft.com.br.whattowatch.data.entities.TVShow

sealed class SeriesResult {
    class Success(val TVShows: List<TVShow>): SeriesResult()
    class ApiError(val code: Int) : SeriesResult()
    object ServerError : SeriesResult()
}
