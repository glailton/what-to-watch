package grsoft.com.br.whattowatch.ui.series.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.di.CoroutineScropeIO
import grsoft.com.br.whattowatch.utils.Resource
import grsoft.com.br.whattowatch.utils.Variables
import kotlinx.coroutines.CoroutineScope

class DetailsViewModel @ViewModelInject constructor(
        repository: TMDbRepository,
        @CoroutineScropeIO private val io: CoroutineScope
) : ViewModel() {
    var connectivityAvailable: Boolean = Variables.isNetworkConnected

    private val _id = MutableLiveData<Int>()

    private val _details = _id.switchMap { id ->
        repository.getDetails(id, "en")
    }
    val details: LiveData<Resource<Details>> get() = _details

    fun start(id: Int) {
        _id.postValue(id)
    }
}