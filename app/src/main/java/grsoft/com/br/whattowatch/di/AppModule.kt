package grsoft.com.br.whattowatch.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import grsoft.com.br.whattowatch.data.remote.TVShowRemoteDataSource
import grsoft.com.br.whattowatch.data.remote.TVShowsService
import grsoft.com.br.whattowatch.data.repository.TVShowRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.episodate.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTvShowsService(retrofit: Retrofit): TVShowsService = retrofit.create(TVShowsService::class.java)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TVShowRemoteDataSource) =
        TVShowRepository(remoteDataSource)

}