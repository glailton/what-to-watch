package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "network")
data class Network(
        @PrimaryKey
        val id: Int = 0,
        val logoPath: String? = "",
        val name: String = "",
        val originCountry: String = ""
)