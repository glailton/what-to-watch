package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
)