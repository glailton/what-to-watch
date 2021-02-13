package grsoft.com.br.whattowatch.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.data.entities.Genre
import grsoft.com.br.whattowatch.data.entities.TVShow

@Dao
interface TMDbDao {

    //Series
    @Query("SELECT * FROM tv_shows")
    fun getAllSeries() : DataSource.Factory<Int, TVShow>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getSeriesById(id: Int): LiveData<TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tvShows: List<TVShow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TVShow)

    //Genres
    @Query("SELECT * FROM genres")
    fun getAllGenres() : LiveData<List<Genre>>

    @Query("SELECT * FROM genres WHERE id = :id")
    fun getGenreById(id: Int): LiveData<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllGenres(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: Genre)


    //Details
    @Query("SELECT * FROM details WHERE id = :id")
    fun getDetails(id: Int): LiveData<Details>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(genres: List<Details>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details: Details)
}
