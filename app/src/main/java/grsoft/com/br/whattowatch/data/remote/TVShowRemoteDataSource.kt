package grsoft.com.br.whattowatch.data.remote

import javax.inject.Inject

class TVShowRemoteDataSource @Inject constructor(
    private val tvShowService: TVShowService
): BaseDataSource() {

    suspend fun getSeries(page: String) = getResult { tvShowService.getSeries(page) }
//    suspend fun getMostPopularSeries(page: String): SeriesBodyResponse = tvShowsService.getMostPopularSeries(page)
}