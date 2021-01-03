package grsoft.com.br.whattowatch.data.repository

import grsoft.com.br.whattowatch.data.model.Series
import grsoft.com.br.whattowatch.data.response.SeriesResult

interface SeriesRepository {
    fun getSeries(seriesResultCallback: (result: SeriesResult) -> Unit)

//    fun getSeries() : List<Series>
}