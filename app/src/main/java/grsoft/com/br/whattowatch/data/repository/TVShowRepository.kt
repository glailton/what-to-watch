package grsoft.com.br.whattowatch.data.repository

import grsoft.com.br.whattowatch.data.local.TVShowDao
import grsoft.com.br.whattowatch.data.remote.TVShowRemoteDataSource
import grsoft.com.br.whattowatch.utils.mapper
import grsoft.com.br.whattowatch.utils.performGetOperation
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val remoteDataSource: TVShowRemoteDataSource,
    private val localDataSource: TVShowDao
) {
    fun getSeries(page: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllSeries() },
        networkCall = { remoteDataSource.getSeries(page) },
        saveCallResult = { localDataSource.insertAll(mapper(it.results))}
    )

}