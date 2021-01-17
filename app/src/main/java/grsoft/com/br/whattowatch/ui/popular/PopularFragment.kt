package grsoft.com.br.whattowatch.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.PopularFragmentBinding
import grsoft.com.br.whattowatch.ui.adapters.SeriesAdapter
import grsoft.com.br.whattowatch.utils.Resource
import timber.log.Timber

@AndroidEntryPoint
class PopularFragment : Fragment(), SeriesAdapter.TVShowItemListener {
    private var _binding: PopularFragmentBinding? = null
    private val binding get() = _binding!!
    private val popularViewModel: PopularViewModel by viewModels()
    private lateinit var adapter: SeriesAdapter
    private var mapGenre: Map<Int, String> = hashMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = PopularFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
//        adapter = FeedAdapter(this)
        adapter = SeriesAdapter(this)
        binding.recyclerPopular.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerPopular.adapter = adapter

    }

    private fun setupObservers() {
        popularViewModel.tvShows.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty())
//                        adapter.setItems(ArrayList(popularViewModel.convertToFeed(ArrayList(it.data), mapGenre)))
                        adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })

        popularViewModel.genres.observe(viewLifecycleOwner, Observer { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    if (!resource.data.isNullOrEmpty())  {
                        val genres = resource.data
                        mapGenre = genres.associateBy({it.id}, {it.name})
                        Timber.d(mapGenre.toString())
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClicked(tvShow: TVShow) {
        Toast.makeText(requireContext(), tvShow.name, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}