package grsoft.com.br.whattowatch.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import grsoft.com.br.whattowatch.data.entities.TVShow

@Dao
interface TVShowDao {
    @Query("SELECT * FROM tv_shows")
    fun getAllSeries() : LiveData<List<TVShow>>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    fun getSeriesById(id: Int): LiveData<TVShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(TVShows: List<TVShow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(TVShow: TVShow)
}
