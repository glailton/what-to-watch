package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "videos")
data class Videos(
    @PrimaryKey
    val id: Int,
    val results: List<VideoList>
)

data class VideoList(
        val id: String,
        val iso31661: String,
        val iso6391: String,
        val key: String,
        val name: String,
        val site: String,
        val size: Int,
        val type: String)
