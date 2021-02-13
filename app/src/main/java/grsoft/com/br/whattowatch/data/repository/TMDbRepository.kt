package grsoft.com.br.whattowatch.data.repository

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.local.TMDbDao
import grsoft.com.br.whattowatch.data.remote.TMDbPageDataSourceFactory
import grsoft.com.br.whattowatch.data.remote.TMDbRemoteDataSource
import grsoft.com.br.whattowatch.utils.mapperResultToDetails
import grsoft.com.br.whattowatch.utils.mapperResultToGenre
import grsoft.com.br.whattowatch.utils.performGetOperation
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
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

    fun getDetails(id: Int, language: String) = performGetOperation(
            databaseQuery = { localDataSource.getDetails(id) },
            networkCall = { remoteDataSource.getDetails(id, language) },
            saveCallResult = { localDataSource.insertDetails(mapperResultToDetails(it))}
    )

    fun observePagedTvShow(connectivityAvailable: Boolean,
                           coroutineScope: CoroutineScope,
                           fragment: Fragment) =
            if (connectivityAvailable) observeRemotePagedTvShow(coroutineScope, fragment)
            else observeLocalPagedTvShow()

    private fun observeRemotePagedTvShow(coroutineScope: CoroutineScope, fragment: Fragment)
        : LiveData<PagedList<TVShow>> {
        val dataSourceFactory = TMDbPageDataSourceFactory(remoteDataSource, localDataSource,
                coroutineScope, fragment)
        return LivePagedListBuilder(dataSourceFactory,
            TMDbPageDataSourceFactory.pagedListConfig()).build()
    }

    private fun observeLocalPagedTvShow()
        : LiveData<PagedList<TVShow>> {

        val dataSourceFactory = localDataSource.getAllSeries()

        return LivePagedListBuilder(dataSourceFactory,
                TMDbPageDataSourceFactory.pagedListConfig()).build()
    }

}