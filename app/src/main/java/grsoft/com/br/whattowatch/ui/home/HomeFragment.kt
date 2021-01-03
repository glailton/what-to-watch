package grsoft.com.br.whattowatch.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.repository.SeriesApiDataSource
import grsoft.com.br.whattowatch.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeViewModel: HomeViewModel = HomeViewModel.ViewModelFactory(SeriesApiDataSource())
            .create(HomeViewModel::class.java)

        homeViewModel.seriesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { seriesList ->
                with(binding.recyclerHome) {
                    layoutManager = GridLayoutManager(context, 2)
//                    setHasFixedSize(true)
                    adapter = HomeAdapter(seriesList) { series ->
                        Toast.makeText(context, series.name, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        homeViewModel.viewFlipperData.observe(viewLifecycleOwner, Observer {
            it?.let { viewFlipper ->
                binding.viewFlipperBooks.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessage ->
                    binding.textViewError.text = getString(errorMessage)
                }
            }
        })

        homeViewModel.getSeries()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}