package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows")
data class TVShow(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val firstAirDate: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val backdropPath: String
)