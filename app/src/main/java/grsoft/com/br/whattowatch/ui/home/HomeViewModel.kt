package grsoft.com.br.whattowatch.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.data.repository.TVShowRepository
import grsoft.com.br.whattowatch.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(
    private val repository: TVShowRepository
) : ViewModel() {
    private var _series = MutableLiveData<Resource<MutableList<TVShow>>>()
    val TVShow: LiveData<Resource<MutableList<TVShow>>> get() = _series

    init {
        _series.postValue(Resource.loading(null))

//        fetchSeries("1")
    }

    val tvShows = repository.getSeries("1")

//    private fun fetchSeries(page: String) {
//        viewModelScope.launch {
//            try {
//                val TVShowList: MutableList<TVShow> = mutableListOf()
//                repository.getSeries(page).tvShows.forEach { s ->
//                    val seriesModel = TVShow(s.id, s.name, s.permalink, s.startDate, s.endDate,
//                        s.country, s.network, s.status, s.imageThumbnailPath)
//                    TVShowList.add(seriesModel)
//                }
//                _series.value = Resource.success(TVShowList)
//            } catch (e: Exception) {
//                _series.value = Resource.error(e.message.toString(), null)
//            }
//        }
//    }

    class ViewModelFactory(private val dataSource: TVShowRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown viewModel class")
        }
    }

}