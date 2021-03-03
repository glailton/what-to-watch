package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import grsoft.com.br.whattowatch.data.response.videos.Result
import kotlinx.parcelize.RawValue

@Entity(tableName = "videos")
data class Videos(
    @PrimaryKey
    val id: Int,
    val results: @RawValue List<VideoList>
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
