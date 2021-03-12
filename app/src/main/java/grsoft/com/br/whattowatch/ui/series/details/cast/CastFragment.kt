package grsoft.com.br.whattowatch.ui.series.details.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.databinding.CastFragmentBinding
import grsoft.com.br.whattowatch.ui.series.details.adapters.CastAdapter
import grsoft.com.br.whattowatch.utils.Resource

@AndroidEntryPoint
class CastFragment : Fragment() {

    private var _binding: CastFragmentBinding? = null
    private val binding get() = _binding!!
    private val castViewModel: CastViewModel by viewModels()
    private lateinit var adapter: CastAdapter

    companion object {
        fun newInstance(details: Details) = CastFragment().apply {
            arguments = Bundle().apply {
                putParcelable("details", details)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = CastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Details>("details")?.let {
            castViewModel.start(it.id)
            setupObservers()
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        adapter = CastAdapter().apply {
            onItemClick = {
                var cast = adapter.getItem(it)

            }
        }
        binding.recyclerViewCast.itemAnimator = DefaultItemAnimator()
        val layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewCast.layoutManager = layoutManager
        binding.recyclerViewCast.adapter = adapter
    }

    private fun setupObservers() {
        castViewModel.staff.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        if (resource.data.cast.isNotEmpty()) {
                            binding.castSize.text = getString(R.string.cast_size_text, resource.data.cast.size)
                            adapter.setItems(resource.data.cast)
                        }
                    }
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}