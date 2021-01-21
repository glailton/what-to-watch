package grsoft.com.br.whattowatch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.local.TMDbDao
import grsoft.com.br.whattowatch.data.remote.TMDbPageDataSourceFactory
import grsoft.com.br.whattowatch.data.remote.TMDbRemoteDataSource
import grsoft.com.br.whattowatch.utils.mapperResultToGenre
import grsoft.com.br.whattowatch.utils.mapperResultToTvShow
import grsoft.com.br.whattowatch.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class TMDbRepository @Inject constructor(
    private val remoteDataSource: TMDbRemoteDataSource,
    private val localDataSource: TMDbDao
) {
//    fun getSeries() = performGetOperation(
//        databaseQuery = { localDataSource.getAllSeries() },
//        networkCall = { remoteDataSource.getSeries("1") },
//        saveCallResult = { localDataSource.insertAll(mapperResultToTvShow(it.results))}
//    ).distinctUntilChanged()

    fun getGenres(language: String) = performGetOperation(
        databaseQuery = { localDataSource.getAllGenres() },
        networkCall = { remoteDataSource.getGenres(language) },
        saveCallResult = { localDataSource.insertAllGenres(mapperResultToGenre(it.genres))}
    )

    fun observePagedTvShow(connectivityAvailable: Boolean,
                           coroutineScope: CoroutineScope) =
            if (connectivityAvailable) observeRemotePagedTvShow(coroutineScope)
            else observeLocalPagedTvShow(coroutineScope)

    private fun observeRemotePagedTvShow(coroutineScope: CoroutineScope)
        : LiveData<PagedList<TVShow>> {
        val dataSourceFactory = TMDbPageDataSourceFactory(remoteDataSource, localDataSource,
                coroutineScope)
        val tv = LivePagedListBuilder(dataSourceFactory,
            TMDbPageDataSourceFactory.pagedListConfig()).build()
        return tv
    }

    private fun observeLocalPagedTvShow(coroutineScope: CoroutineScope)
        : LiveData<PagedList<TVShow>> {

        val dataSourceFactory = localDataSource.getAllSeries()

        return LivePagedListBuilder(dataSourceFactory,
                TMDbPageDataSourceFactory.pagedListConfig()).build()
    }

}