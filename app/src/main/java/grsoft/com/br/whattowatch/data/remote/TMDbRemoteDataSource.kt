package grsoft.com.br.whattowatch.data.remote

import javax.inject.Inject

class TMDbRemoteDataSource @Inject constructor(
    private val tmdbService: TMDbService
): BaseDataSource() {

    suspend fun getPopularSeries(page: String) = getResult { tmdbService.getPopularSeries(page) }
    suspend fun getTopRatedSeries(page: String) = getResult { tmdbService.getTopRatedSeries(page) }
    suspend fun getOnTheAirSeries(page: String) = getResult { tmdbService.getOnTheAirSeries(page) }
    suspend fun getGenres(language: String) = getResult { tmdbService.getGenres(language) }
    suspend fun getDetails(id: Int, language: String) = getResult { tmdbService.getDetails(id, language) }
}