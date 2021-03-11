package grsoft.com.br.whattowatch.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import grsoft.com.br.whattowatch.data.entities.*

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

    //Network
    @Query("SELECT * FROM network WHERE id = :id")
    fun getNetwork(id: Int): LiveData<Network>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNetworks(networks: List<Network>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNetwork(network: Network)

    //Videos
    @Query("SELECT * FROM videos WHERE id = :id")
    fun getVideo(id: Int): LiveData<Videos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videos: Videos)

    //Cast
    @Query("SELECT * FROM staff WHERE id = :id")
    fun getStaff(id: Int): LiveData<Staff>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStaff(staff: Staff)
}
