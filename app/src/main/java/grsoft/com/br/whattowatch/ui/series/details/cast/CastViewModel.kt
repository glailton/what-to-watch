package grsoft.com.br.whattowatch.ui.series.details.cast

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import grsoft.com.br.whattowatch.data.entities.Staff
import grsoft.com.br.whattowatch.data.repository.TMDbRepository
import grsoft.com.br.whattowatch.utils.Resource

class CastViewModel @ViewModelInject constructor(
    repository: TMDbRepository
): ViewModel() {
    private val _id = MutableLiveData<Int>()

    val staff: LiveData<Resource<Staff>> get() = _staff
    private val _staff = _id.switchMap { id ->
        repository.getStaff(id, "en")
    }

    fun start(id: Int) {
        _id.postValue(id)
    }
}