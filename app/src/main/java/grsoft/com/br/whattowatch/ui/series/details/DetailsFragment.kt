package grsoft.com.br.whattowatch.ui.series.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.databinding.DetailsFragmentBinding
import grsoft.com.br.whattowatch.ui.extensions.loadImage
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

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                kotlin.math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0 -> {
                    binding.showOverviewContent.visibility = GONE
                }
                verticalOffset == 0 -> {
                    binding.showOverviewContent.visibility = VISIBLE
                }
                else -> {
                    binding.showOverviewContent.visibility = VISIBLE
                }
            }
        })

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
                    binding.progressBar.visibility = GONE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = VISIBLE
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
            binding.collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
            binding.showName.text = details.name
            binding.showYearStatus.text = getString(R.string.year_airs_label, details.firstAirDate?.substring(0, 4), details.status)
            binding.imageHeaderTab.loadImage(BASE_URL + details.backdropPath)
            binding.poster.loadImage(BASE_URL + details.posterPath)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}