package grsoft.com.br.whattowatch.data.model

import java.time.LocalDate

data class Series(
    val id: Int = 0,
    val name: String = "",
    val permalink: String = "",
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val country: String = "",
    val network: String = "",
    val status: String = "",
    val thumbnailPath: String = "",
)