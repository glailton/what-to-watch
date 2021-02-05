package grsoft.com.br.whattowatch.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import grsoft.com.br.whattowatch.data.entities.Genre


class Converters {
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
}