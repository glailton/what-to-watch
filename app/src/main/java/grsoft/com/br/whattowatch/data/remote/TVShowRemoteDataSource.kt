package grsoft.com.br.whattowatch.data.remote

import grsoft.com.br.whattowatch.data.response.series.SeriesBodyResponse
import javax.inject.Inject

class TVShowRemoteDataSource @Inject constructor(
    private val tvShowsService: TVShowsService
) {

    suspend fun getSeries(page: String): SeriesBodyResponse = tvShowsService.getSeries(page)
    suspend fun getMostPopularSeries(page: String): SeriesBodyResponse = tvShowsService.getMostPopularSeries(page)
}