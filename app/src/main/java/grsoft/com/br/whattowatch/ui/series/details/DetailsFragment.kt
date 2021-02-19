package grsoft.com.br.whattowatch.ui.series.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.databinding.DetailsFragmentBinding
import grsoft.com.br.whattowatch.ui.series.details.about.AboutFragment
import grsoft.com.br.whattowatch.ui.series.details.adapters.ViewPageAdapter
import grsoft.com.br.whattowatch.ui.series.details.episodes.EpisodesFragment
import grsoft.com.br.whattowatch.utils.BASE_URL
import grsoft.com.br.whattowatch.utils.Resource

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        setupToolbarOptions()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = DetailsFragmentArgs.fromBundle(it)
            detailsViewModel.start(safeArgs.id)
            setupObservers()
        }
    }


    private fun setupObservers() {
        detailsViewModel.details.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        bindView(binding, resource.data)
                        setupViewPager(resource.data)
                    }
                    binding.progressBar.visibility = View.GONE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupToolbarOptions() {
        binding.tabToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back)
        binding.tabToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupViewPager(details: Details) {
        var viewPageAdapter = ViewPageAdapter(childFragmentManager)
        viewPageAdapter.addFragment(AboutFragment.newInstance(details), "About")
        viewPageAdapter.addFragment(EpisodesFragment.newInstance(), "Episodes")
        binding.viewpager.adapter = viewPageAdapter
        binding.tabs.setupWithViewPager(binding.viewpager)
    }

    private fun bindView(binding: DetailsFragmentBinding, details: Details) {
        details.apply {
            binding.collapsingToolbar.title = details.name
            Glide.with(this@DetailsFragment)
                    .load(BASE_URL + details.backdropPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.htabHeader);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}