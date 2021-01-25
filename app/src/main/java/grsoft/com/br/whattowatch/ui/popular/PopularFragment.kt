package grsoft.com.br.whattowatch.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.data.entities.TVShow
import grsoft.com.br.whattowatch.databinding.PopularFragmentBinding
import grsoft.com.br.whattowatch.ui.adapters.SeriesAdapter
import grsoft.com.br.whattowatch.utils.ConnectivityUtil
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
        popularViewModel.connectivityAvailable = ConnectivityUtil.isConnected(requireContext())

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
        popularViewModel.tvShows.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            Timber.d(it.toString())
            adapter.submitList(it)
//            TODO Use this code when grouping tv shows by genre
//            adapter.setItems(ArrayList(popularViewModel.convertToFeed(ArrayList(it.data), mapGenre)))
//            if (!it.isNullOrEmpty()) adapter.setItems(ArrayList(it))
        }

//        popularViewModel.genres.observe(viewLifecycleOwner, Observer { resource ->
//            when (resource.status) {
//                Resource.Status.SUCCESS -> {
//                    if (!resource.data.isNullOrEmpty())  {
//                        val genres = resource.data
//                        mapGenre = genres.associateBy({it.id}, {it.name})
//                        Timber.d(mapGenre.toString())
//                    }
//                }
//                Resource.Status.ERROR ->
//                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
//
//                Resource.Status.LOADING ->
//                    binding.progressBar.visibility = View.VISIBLE
//            }
//        })
    }

    override fun onClicked(tvShow: TVShow) {
        Toast.makeText(requireContext(), tvShow.name, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}