package grsoft.com.br.whattowatch.data.remote

import grsoft.com.br.whattowatch.data.response.details.SeriesDetailsBodyResponse
import grsoft.com.br.whattowatch.data.response.series.SeriesBodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsService {
    @GET("search")
    suspend fun getSeries(@Query("page") page: String): SeriesBodyResponse

    @GET("search")
    suspend fun getSeriesByName(@Query("q") name: String,
                        @Query("page") page: String): Response<SeriesBodyResponse>

    @GET("most-popular")
    suspend fun getMostPopularSeries(@Query("page") page: String): Response<SeriesBodyResponse>

    @GET("show-details")
    suspend fun getShowDetails(@Query("q") name: String): Response<SeriesDetailsBodyResponse>
}
