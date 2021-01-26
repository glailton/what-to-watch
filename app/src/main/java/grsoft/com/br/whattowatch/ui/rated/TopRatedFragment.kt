package grsoft.com.br.whattowatch.ui.rated

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.PopularFragmentBinding
import grsoft.com.br.whattowatch.databinding.TopRatedFragmentBinding
import grsoft.com.br.whattowatch.ui.adapters.SeriesAdapter
import grsoft.com.br.whattowatch.ui.popular.PopularViewModel
import timber.log.Timber

@AndroidEntryPoint
class TopRatedFragment : Fragment(), SeriesAdapter.TVShowItemListener {

    private var _binding: TopRatedFragmentBinding? = null
    private val binding get() = _binding!!
    private val topRatedViewModel: TopRatedViewModel by viewModels()
    private lateinit var adapter: SeriesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = TopRatedFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SeriesAdapter(this)
        binding.recyclerTopRated.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerTopRated.adapter = adapter

    }

    private fun setupObservers() {
        topRatedViewModel.topRatedTvShows.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClicked(tvShow: TVShow) {
        Toast.makeText(requireContext(), tvShow.name, Toast.LENGTH_LONG).show()
    }
}