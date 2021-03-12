package grsoft.com.br.whattowatch.ui.series.details.about

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import grsoft.com.br.whattowatch.data.entities.Staff
import grsoft.com.br.whattowatch.data.entities.Videos
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.utils.Resource

class AboutViewModel @ViewModelInject constructor(
        repository: TMDbRepository
): ViewModel() {
    private val _id = MutableLiveData<Int>()

    val videos: LiveData<Resource<Videos>> get() = _videos
    private val _videos = _id.switchMap { id ->
        repository.getVideos(id, "en")
    }

    val staff: LiveData<Resource<Staff>> get() = _staff
    private val _staff = _id.switchMap { id ->
        repository.getStaff(id, "en")
    }

    fun start(id: Int) {
        _id.postValue(id)
    }
}