package grsoft.com.br.whattowatch.data

import grsoft.com.br.whattowatch.data.model.Series

sealed class SeriesResult {
    class Success(val series: List<Series>): SeriesResult()
    class ApiError(val code: Int) : SeriesResult()
    object ServerError : SeriesResult()
}
