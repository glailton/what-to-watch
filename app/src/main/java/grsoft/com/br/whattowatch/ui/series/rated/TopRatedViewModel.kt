package grsoft.com.br.whattowatch.ui.series.rated

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.di.CoroutineScropeIO
import grsoft.com.br.whattowatch.utils.Variables
import kotlinx.coroutines.CoroutineScope

class TopRatedViewModel @ViewModelInject constructor(
        repository: TMDbRepository,
        @CoroutineScropeIO private val io: CoroutineScope
) : ViewModel() {
    var connectivityAvailable: Boolean = Variables.isNetworkConnected

    val topRatedTvShows = repository.observePagedTvShow(
            connectivityAvailable, io, TopRatedFragment())
}