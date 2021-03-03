package grsoft.com.br.whattowatch.data.response.network


import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("headquarters")
    val headquarters: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)