package grsoft.com.br.whattowatch.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import grsoft.com.br.whattowatch.data.model.Series
import grsoft.com.br.whattowatch.data.response.SeriesResult
import java.time.LocalDate

class SeriesApiDataSource: SeriesRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSeries(seriesResultCallback: (result: SeriesResult) -> Unit) {
        val seriesList = listOf(Series(
            18428, "The Mentalist", "the-mentalist",
            LocalDate.of(2008, 9,23), null,
            "US", "CBS", "Ended",
            "https://static.episodate.com/images/tv-show/thumbnail/18428.jpg"),
        Series(
            51543, "Star Trek: Discovery", "star-trek-cbs",
            LocalDate.of(2017, 9,24), null,
            "US", "CBS All Access", "Running",
            "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"),
            Series(
                51543, "Star Trek: Discovery", "star-trek-cbs",
                LocalDate.of(2017, 9,24), null,
                "US", "CBS All Access", "Running",
                "https://static.episodate.com/images/tv-show/thumbnail/51543.jpg"))

        seriesResultCallback(SeriesResult.Success(seriesList))
    }
}