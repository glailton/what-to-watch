package grsoft.com.br.whattowatch.ui.series.ontheair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.databinding.OnTheAirFragmentBinding
import grsoft.com.br.whattowatch.ui.series.adapters.SeriesAdapter

@AndroidEntryPoint
class OnTheAirFragment : Fragment() {

    private var _binding: OnTheAirFragmentBinding? = null
    private val binding get() = _binding!!
    private val onTheAirViewModel: OnTheAirViewModel by viewModels()
    private lateinit var adapter: SeriesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = OnTheAirFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SeriesAdapter()
        binding.recyclerOnTheAir.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerOnTheAir.adapter = adapter

    }

    private fun setupObservers() {
        onTheAirViewModel.onTheAirTvShows.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}