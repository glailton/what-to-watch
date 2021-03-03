package grsoft.com.br.whattowatch.ui.series.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.data.entities.Videos
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.utils.Resource

class DetailsViewModel @ViewModelInject constructor(
        repository: TMDbRepository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()
    val details: LiveData<Resource<Details>> get() = _details
    private val _details = _id.switchMap { id ->
        repository.getDetails(id, "en")
    }

    val videos: LiveData<Resource<Videos>> get() = _videos
    private val _videos = _id.switchMap { id ->
        repository.getVideos(id, "en")
    }

    fun start(id: Int) {
        _id.postValue(id)
    }

}