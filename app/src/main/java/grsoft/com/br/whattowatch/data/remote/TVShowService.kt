package grsoft.com.br.whattowatch.data.remote

import grsoft.com.br.whattowatch.BuildConfig
import grsoft.com.br.whattowatch.data.response.details.SeriesDetailsBodyResponse
import grsoft.com.br.whattowatch.data.response.series.TVShowBodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowService {
    @GET("tv/popular")
    suspend fun getSeries(
            @Query("page") page: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TVShowBodyResponse>

    @GET("search")
    suspend fun getSeriesByName(@Query("q") name: String,
                        @Query("page") page: String): Response<TVShowBodyResponse>

}
