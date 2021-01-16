package grsoft.com.br.whattowatch.ui.home

import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import grsoft.com.br.whattowatch.data.entities.Genre
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.models.FeedItem
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.utils.Resource
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(
    private val repository: TMDbRepository
) : ViewModel() {

    val tvShows = repository.getSeries("1")
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
}