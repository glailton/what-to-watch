package grsoft.com.br.whattowatch.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import grsoft.com.br.whattowatch.data.entities.*


class Converters {
    private val gson: Gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        return string.split(",")
    }

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromIntToList(string: String): List<Int> {
        return if (!string.isNullOrEmpty())
            string.split(",").map { it.toInt() }
        else listOf()
    }

    @TypeConverter
    fun fromGenreToString(genres: List<Genre>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun fromStringToGenre(genres: String): List<Genre> {
        val genresType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(genres, genresType)
    }

    @TypeConverter
    fun fromNetworkToString(network: List<Network>): String {
        return Gson().toJson(network)
    }

    @TypeConverter
    fun fromStringToNetwork(network: String): List<Network> {
        val networkType = object : TypeToken<List<Network>>() {}.type
        return Gson().fromJson(network, networkType)
    }

    @TypeConverter
    fun fromVideoListToString(videos: List<VideoList>): String {
        return Gson().toJson(videos)
    }

    @TypeConverter
    fun fromStringToVideoList(videos: String): List<VideoList> {
        val networkType = object : TypeToken<List<VideoList>>() {}.type
        return Gson().fromJson(videos, networkType)
    }

    @TypeConverter
    fun fromCompaniesListToString(companies: List<ProductionCompany>): String {
        return Gson().toJson(companies)
    }

    @TypeConverter
    fun fromStringToCompaniesList(companies: String): List<ProductionCompany> {
        val networkType = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson(companies, networkType)
    }

    @TypeConverter
    fun fromCastListToString(cast: List<CastList>): String {
        return Gson().toJson(cast)
    }

    @TypeConverter
    fun fromStringToCastList(cast: String): List<CastList> {
        val networkType = object : TypeToken<List<CastList>>() {}.type
        return Gson().fromJson(cast, networkType)
    }

    @TypeConverter
    fun fromCrewListToString(crew: List<CrewList>): String {
        return Gson().toJson(crew)
    }

    @TypeConverter
    fun fromStringToCrewList(crew: String): List<CrewList> {
        val networkType = object : TypeToken<List<CrewList>>() {}.type
        return Gson().fromJson(crew, networkType)
    }

    @TypeConverter
    fun toGenderType(value: Int) = enumValues<GenderType>()[value]

    @TypeConverter
    fun fromGenderType(value: GenderType) = value.ordinal

//    @TypeConverter
//    fun stringToNetworkList(data: String?): List<Network?>? {
//        if (data == null) {
//            return Collections.emptyList()
//        }
//        val listType: Type = object : TypeToken<List<Network?>?>() {}.type
//        return gson.fromJson(data, listType)
//    }
//
//    @TypeConverter
//    fun networkListToString(someObjects: List<Network>?): String? {
//        return gson.toJson(someObjects)
//    }
}