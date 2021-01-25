package grsoft.com.br.whattowatch.ui.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagedList
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.models.FeedItem
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.data.response.series.TVShowBodyResponse
import grsoft.com.br.whattowatch.di.CoroutineScropeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

class PopularViewModel @ViewModelInject constructor(
    private val repository: TMDbRepository,
    @CoroutineScropeIO private val io: CoroutineScope
) : ViewModel() {

    var connectivityAvailable: Boolean = true

    val tvShows = repository.observePagedTvShow(
            connectivityAvailable, io)

    val genres = repository.getGenres("en")

    fun convertToFeed(tvShows: List<TVShow>, genres: Map<Int, String>): List<FeedItem> {

        val feedItems = mutableListOf<FeedItem>()

        for ((index, genre) in genres) {
            val genreTvShows = tvShows
                    .filter { it.genreIds.contains(index) }
                    .sortedByDescending { it.voteAverage }
            feedItems.add(
                    FeedItem(
                            index.toLong(),
                            genre,
                            genreTvShows
                    )
            )
        }
        return feedItems
    }

    override fun onCleared() {
        super.onCleared()
        io.cancel()
    }
}