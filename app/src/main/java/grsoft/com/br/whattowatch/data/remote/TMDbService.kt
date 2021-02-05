package grsoft.com.br.whattowatch.data.remote

import grsoft.com.br.whattowatch.BuildConfig
import grsoft.com.br.whattowatch.data.response.details.TVShowDetailsResponse
import grsoft.com.br.whattowatch.data.response.genre.GenreResponse
import grsoft.com.br.whattowatch.data.response.series.TVShowBodyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbService {
    @GET("tv/popular")
    suspend fun getPopularSeries(
            @Query("page") page: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TVShowBodyResponse>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
            @Query("page") page: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TVShowBodyResponse>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirSeries(
            @Query("page") page: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY): Response<TVShowBodyResponse>

    @GET("search")
    suspend fun getSeriesByName(@Query("q") name: String,
                        @Query("page") page: String): Response<TVShowBodyResponse>

    @GET("genre/tv/list")
    suspend fun getGenres(
        @Query("language") language: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<GenreResponse>

    @GET("tv/{tv_id}")
    suspend fun getDetails(
            @Path("tv_id") id: Int,
            @Query("language") language: String,
            @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<TVShowDetailsResponse>

}
