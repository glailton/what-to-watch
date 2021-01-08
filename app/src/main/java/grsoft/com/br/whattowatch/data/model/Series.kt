package grsoft.com.br.whattowatch.data.model

import java.time.LocalDate

data class Series(
    val id: Int = 0,
    val name: String = "",
    val permalink: String = "",
    val startDate: String,
    val endDate: Any?,
    val country: String = "",
    val network: String = "",
    val status: String = "",
    val thumbnailPath: String = "",
)