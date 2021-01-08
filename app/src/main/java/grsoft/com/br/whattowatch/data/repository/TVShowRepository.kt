package grsoft.com.br.whattowatch.data.repository

import grsoft.com.br.whattowatch.data.remote.TVShowRemoteDataSourceImpl
import grsoft.com.br.whattowatch.data.response.series.SeriesBodyResponse
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val remoteDataSource: TVShowRemoteDataSourceImpl
) {
    suspend fun getSeries(page: String): SeriesBodyResponse {
        return remoteDataSource.getSeries(page)
    }
}