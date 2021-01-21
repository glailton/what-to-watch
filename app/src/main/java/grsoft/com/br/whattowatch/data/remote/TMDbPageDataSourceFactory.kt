package grsoft.com.br.whattowatch.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.local.TMDbDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class TMDbPageDataSourceFactory @Inject constructor(
        private val dataSource: TMDbRemoteDataSource,
        private val dao: TMDbDao,
        private val scope: CoroutineScope
) : DataSource.Factory<Int, TVShow>() {

    private val liveData = MutableLiveData<TMDbPageDataSource>()

    override fun create(): DataSource<Int, TVShow> {
        val source = TMDbPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()
    }
}