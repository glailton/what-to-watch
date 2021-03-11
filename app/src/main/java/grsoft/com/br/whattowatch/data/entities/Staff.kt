package grsoft.com.br.whattowatch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff")
data class Staff(
        @PrimaryKey
        val id: Int,
        val cast: List<CastList>,
        val crew: List<CrewList>
)

data class CastList(
        val id: Int,
        val adult: Boolean,
        val character: String,
        val creditId: String,
        val gender: GenderType,
        val knownForDepartment: String? = "",
        val name: String,
        val order: Int,
        val originalName: String,
        val popularity: Double,
        val profilePath: String? = ""
)

data class CrewList(
        val id: Int,
        val adult: Boolean,
        val creditId: String,
        val department: String,
        val gender: GenderType,
        val job: String,
        val knownForDepartment: String? = "",
        val name: String,
        val originalName: String,
        val popularity: Double,
        val profilePath: String? = ""
)

enum class GenderType(val value: Int) {
    UNKNOWN(0),
    FEMALE(1),
    MALE(2)
}