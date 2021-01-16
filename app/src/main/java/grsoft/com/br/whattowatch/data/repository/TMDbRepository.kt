package grsoft.com.br.whattowatch.data.repository

import grsoft.com.br.whattowatch.data.local.TMDbDao
import grsoft.com.br.whattowatch.data.remote.TMDbRemoteDataSource
import grsoft.com.br.whattowatch.utils.mapperResultToGenre
import grsoft.com.br.whattowatch.utils.mapperResultToTvShow
import grsoft.com.br.whattowatch.utils.performGetOperation
import javax.inject.Inject

class TMDbRepository @Inject constructor(
    private val remoteDataSource: TMDbRemoteDataSource,
    private val localDataSource: TMDbDao
) {
    fun getSeries(page: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllSeries() },
        networkCall = { remoteDataSource.getSeries(page) },
        saveCallResult = { localDataSource.insertAll(mapperResultToTvShow(it.results))}
    )

    fun getGenres(language: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllGenres() },
        networkCall = { remoteDataSource.getGenres(language) },
        saveCallResult = { localDataSource.insertAllGenres(mapperResultToGenre(it.genres))}
    )

}