package grsoft.com.br.whattowatch.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.model.Series
import grsoft.com.br.whattowatch.data.repository.SeriesRepository
import grsoft.com.br.whattowatch.data.response.SeriesResult
import java.lang.IllegalArgumentException

class HomeViewModel(private val dataSource: SeriesRepository) : ViewModel() {
    val seriesLiveData: MutableLiveData<List<Series>> = MutableLiveData()
    val viewFlipperData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getSeries() {
        dataSource.getSeries { result: SeriesResult ->
            when(result) {
                is SeriesResult.Success -> {
                    seriesLiveData.value = result.series
                    viewFlipperData.value = Pair(VIEW_FLIPPER_SERIES, null)
                }
                is SeriesResult.ApiError -> {
                    if (result.code == 401) {
                        viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.series_error_401)
                    } else {
                        viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.series_error_400_generic)
                    }
                }
                is SeriesResult.ServerError -> {
                    viewFlipperData.value = Pair(VIEW_FLIPPER_ERROR, R.string.series_error_500_generic)
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: SeriesRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown viewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_SERIES = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}