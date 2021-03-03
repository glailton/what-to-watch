package grsoft.com.br.whattowatch.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import grsoft.com.br.whattowatch.data.entities.Genre
import grsoft.com.br.whattowatch.data.entities.Network
import grsoft.com.br.whattowatch.data.entities.VideoList
import java.lang.reflect.Type
import java.util.*


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