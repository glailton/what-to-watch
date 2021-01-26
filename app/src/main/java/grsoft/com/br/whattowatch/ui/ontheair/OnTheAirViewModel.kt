package grsoft.com.br.whattowatch.ui.ontheair

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.di.CoroutineScropeIO
import grsoft.com.br.whattowatch.utils.Variables
import kotlinx.coroutines.CoroutineScope

class OnTheAirViewModel @ViewModelInject constructor(
        repository: TMDbRepository,
        @CoroutineScropeIO private val io: CoroutineScope
) : ViewModel() {
    var connectivityAvailable: Boolean = Variables.isNetworkConnected

    val onTheAirTvShows = repository.observePagedTvShow(
            connectivityAvailable, io, OnTheAirFragment())
}