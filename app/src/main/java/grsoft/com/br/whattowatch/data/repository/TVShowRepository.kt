package grsoft.com.br.whattowatch.data.repository

import grsoft.com.br.whattowatch.data.remote.TVShowRemoteDataSource
import grsoft.com.br.whattowatch.data.response.series.SeriesBodyResponse
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val remoteDataSource: TVShowRemoteDataSource
) {
    suspend fun getSeries(page: String): SeriesBodyResponse {
        return remoteDataSource.getSeries(page)
    }

    suspend fun getMostPopularSeries(page: String): SeriesBodyResponse {
        return remoteDataSource.getMostPopularSeries(page)
    }
}