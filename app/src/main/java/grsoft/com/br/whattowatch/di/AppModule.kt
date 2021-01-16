package grsoft.com.br.whattowatch.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import grsoft.com.br.whattowatch.data.local.AppDatabase
import grsoft.com.br.whattowatch.data.local.TMDbDao
import grsoft.com.br.whattowatch.data.remote.TMDbRemoteDataSource
import grsoft.com.br.whattowatch.data.remote.TMDbService
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https:api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTvShowService(retrofit: Retrofit): TMDbService = retrofit.create(TMDbService::class.java)

    @Singleton
    @Provides
    fun provideTVShowRemoteDataSource(tmdbService: TMDbService) = TMDbRemoteDataSource(tmdbService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideTVShowDao(db: AppDatabase) = db.seriesDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TMDbRemoteDataSource,
                          localDataSource: TMDbDao) =
        TMDbRepository(remoteDataSource, localDataSource)

}