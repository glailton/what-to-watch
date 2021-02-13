package grsoft.com.br.whattowatch.data.remote

import androidx.fragment.app.Fragment
import androidx.paging.PageKeyedDataSource
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.local.TMDbDao
import grsoft.com.br.whattowatch.data.response.series.TVShowBodyResponse
import grsoft.com.br.whattowatch.ui.series.ontheair.OnTheAirFragment
import grsoft.com.br.whattowatch.ui.series.popular.PopularFragment
import grsoft.com.br.whattowatch.ui.rated.TopRatedFragment
import grsoft.com.br.whattowatch.utils.Resource
import grsoft.com.br.whattowatch.utils.mapperResultToTvShow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class TMDbPageDataSource @Inject constructor(
        private val dataSource: TMDbRemoteDataSource,
        private val dao: TMDbDao,
        private val scope: CoroutineScope,
        private val fragment: Fragment
) : PageKeyedDataSource<Int, TVShow>() {
    private lateinit var response: Resource<TVShowBodyResponse>

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TVShow>) {
        fetchData(1) {
            Timber.v("loadInitial")
            callback.onResult(it, null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TVShow>) {
        val page = params.key
        Timber.v("loadBefore %s", page.toString())
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TVShow>) {
        val page = params.key
        Timber.v("loadAfter %s", page.toString())
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<TVShow>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            when (fragment) {
                is PopularFragment -> {
                    response = dataSource.getPopularSeries(page.toString())
                }
                is TopRatedFragment -> {
                    response = dataSource.getTopRatedSeries(page.toString())
                }
                is OnTheAirFragment -> {
                    response = dataSource.getOnTheAirSeries(page.toString())
                }
            }

            when (response.status) {
                Resource.Status.SUCCESS -> {
                    val results = response.data!!.results
                    dao.insertAll(mapperResultToTvShow(results))
                    callback(mapperResultToTvShow(results))
                }
                Resource.Status.ERROR -> postError(response.message!!)

            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
        // TODO network error handling
        //networkState.postValue(NetworkState.FAILED)
    }

}