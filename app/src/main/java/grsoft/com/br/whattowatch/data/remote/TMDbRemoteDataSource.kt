package grsoft.com.br.whattowatch.data.remote

import javax.inject.Inject

class TMDbRemoteDataSource @Inject constructor(
    private val tmdbService: TMDbService
): BaseDataSource() {

    suspend fun getSeries(page: String) = getResult { tmdbService.getSeries(page) }
    suspend fun getGenres(language: String) = getResult { tmdbService.getGenres(language) }
}