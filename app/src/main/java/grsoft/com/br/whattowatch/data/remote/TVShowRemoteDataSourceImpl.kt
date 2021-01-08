package grsoft.com.br.whattowatch.data.remote

import grsoft.com.br.whattowatch.data.response.series.SeriesBodyResponse
import javax.inject.Inject

class TVShowRemoteDataSourceImpl @Inject constructor(
    private val tvShowsService: TVShowsService
) {

    suspend fun getSeries(page: String): SeriesBodyResponse = tvShowsService.getSeries(page)
}