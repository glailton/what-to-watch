package grsoft.com.br.whattowatch.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Entity(tableName = "details")
@Parcelize
data class Details(
        @PrimaryKey
        val id: Int,
        val backdropPath: String,
        val createdById: Int?,
        val episodeRunTime: List<Int>,
        val firstAirDate: String,
        val genres: @RawValue List<Genre>,
        val homepage: String,
        val inProduction: Boolean,
        val languages: List<String>,
        val lastAirDate: String,
        val lastEpisodeToAirId: Int,
        val name: String,
        val nextEpisodeToAirId: Int?,
        val networksId: List<Int>,
        val numberOfEpisodes: Int,
        val numberOfSeasons: Int,
        val originCountry: List<String>,
        val originalLanguage: String,
        val originalName: String,
        val overview: String,
        val popularity: Double,
        val posterPath: String,
        val productionCompaniesId: List<Int>,
        val productionCountries: List<String>,
        val seasonsId: List<Int>,
        val spokenLanguages: List<String>,
        val status: String,
        val tagline: String,
        val tvShowType: String,
        val voteAverage: Double,
        val voteCount: Int
): Parcelable

//data class CreatedBy(
//        val creditId: String = "",
//        val createdById: Int = 0,
//        val createdByName: String = "",
//        val gender: Int = 0,
//        val profilePath: String? = ""
//) {
//        constructor() : this("", 0, "", 0, "")
//}
//
//data class LastEpisodeToAir(
//        val airDate: String = "",
//        val lastEpisodeNumber: Int = 0,
//        val lastEpisodeToAirId: Int = 0,
//        val lastEpisodeToAirName: String = "",
//        val lastEpisodeToAirOverview: String = "",
//        val productionCode: String = "",
//        val seasonNumber: Int = 0,
//        val stillPath: String = "",
//        val lastEpisodeVoteAverage: Double = 0.0,
//        val lastEpisodeVoteCount: Int = 0
//) {
//        constructor() : this("", 0, 0, "", "", "", 0, "", 0.0, 0)
//}
//
//data class NextEpisodeToAir(
//        val nextEpisodeToAirDate: String = "",
//        val nextEpisodeNumber: Int = 0,
//        val nextEpisodeToAirId: Int = 0,
//        val nextEpisodeToAirName: String = "",
//        val nextEpisodeToAirOverview: String = "",
//        val nextEpisodeToAirProdCode: String = "",
//        val nextEpisodeSeasonNumber: Int = 0,
//        val nextEpisodeStillPath: String = "",
//        val nextEpisodeVoteAverage: Double = 0.0,
//        val nextEpisodeVoteCount: Int = 0
//) {
//        constructor() : this("", 0, 0, "", "", "", 0,"", 0.0, 0)
//}
//
//data class Network(
//        val networkId: Int = 0,
//        val logoPath: String = "",
//        val networkName: String = "",
//        val originCountry: String = ""
//) {
//        constructor() : this(0, "", "", "")
//}
//
//data class ProductionCompany(
//        val prodCompanyId: Int = 0,
//        val prodCompanyLogoPath: String = "",
//        val prodCompanyName: String = "",
//        val prodCompanyOriginCountry: String = ""
//) {
//        constructor() : this(0, "", "", "")
//}
//
//data class ProductionCountry(
//        val isoProdCountry: String = "",
//        val ProdCountryName: String = ""
//) {
//        constructor() : this("", "")
//}
//
//data class Season(
//        val seasonAirDate: String,
//        val episodeCount: Int,
//        val seasonId: Int,
//        val seasonName: String,
//        val seasonOverview: String,
//        val seasonPosterPath: String,
//        val seasonNumber: Int
//) {
//        constructor() : this("", 0, 0, "", "", "", 0)
//}
//
//data class SpokenLanguage(
//        val englishName: String,
//        val spokenLanguageIso: String,
//        val spokenLanguageName: String
//) {
//        constructor() : this("", "", "")
//}