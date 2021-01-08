package grsoft.com.br.whattowatch.data.response.details


import com.google.gson.annotations.SerializedName

data class SeriesDetailsBodyResponse(
    @SerializedName("tvShow")
    val tvShow: TvShow
)