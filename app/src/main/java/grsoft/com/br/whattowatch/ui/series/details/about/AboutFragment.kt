package grsoft.com.br.whattowatch.ui.series.details.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.databinding.AboutFragmentBinding
import grsoft.com.br.whattowatch.ui.series.details.adapters.VideosAdapter
import grsoft.com.br.whattowatch.utils.Resource
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.text.TextUtils
import android.widget.TextView
import grsoft.com.br.whattowatch.ui.extensions.hide
import grsoft.com.br.whattowatch.ui.extensions.setTextList
import grsoft.com.br.whattowatch.utils.YOUTUBE_URL


@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: AboutFragmentBinding? = null
    private val binding get() = _binding!!
    private val aboutViewModel: AboutViewModel by viewModels()
    private lateinit var adapter: VideosAdapter

    companion object {
        fun newInstance(details: Details) = AboutFragment().apply {
            arguments = Bundle().apply {
                putParcelable("details", details)
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = AboutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Details>("details")?.let {
            aboutViewModel.start(it.id)
            setupObservers()
            setupRecyclerView()
            bindView(it)
        }
    }

    private fun setupRecyclerView() {
//        adapter = FeedAdapter(this)
        adapter = VideosAdapter().apply {
            onItemClick = {
                var video = adapter.getItem(it)

                video.let {
                    val intent = Intent(Intent(ACTION_VIEW,
                            Uri.parse(YOUTUBE_URL + video?.key)))
                    startActivity(intent)
                }
            }
        }
        binding.recyclerViewVideos.itemAnimator = DefaultItemAnimator()
        val layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewVideos.layoutManager = layoutManager
        binding.recyclerViewVideos.adapter = adapter

    }

    private fun setupObservers() {
        aboutViewModel.videos.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        if (resource.data.results.isNotEmpty())
                            adapter.setItems(resource.data.results)
                        else
                            binding.labelTrailer.hide()
                    }
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, resource.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                }
            }
        }
    }

    private fun bindView(details: Details) {
        binding.textOverview.text = details.overview
        binding.textDuration.text = getString(R.string.duration_text, details.episodeRunTime.first())
        binding.textLanguage.text = details.originalLanguage?.capitalize()
        binding.textVote.text = details.voteAverage.toString()
        binding.labelVote.text = getString(R.string.votes_text, details.voteCount)
        binding.originalTitleText.text = details.originalName
        binding.firstAirDateText.text = details.firstAirDate
        binding.lastAirDateText.text = details.lastAirDate
        binding.airedEpisodesText.text = details.numberOfEpisodes.toString()
        binding.runtimeText.text = TextUtils.join(", ", details.episodeRunTime)
        binding.showTypeText.text = details.tvShowType
        binding.originalLanguageText.text = details.originalLanguage
        binding.countryText.text = TextUtils.join(", ", details.originCountry)
        binding.companiesText.setTextList(details.companies)
        details.genres.forEach { genre ->
            val chip = Chip(context)
            with(binding) {
                chip.text = genre.name
                if (genreChipGroup.size < details.genres.size) {
                    genreChipGroup.addView(chip)
                }
            }
        }
        details.networks.forEach { network ->
            //TODO Add image on chip
            val chip = Chip(context)
            with(binding) {
                chip.text = network.name
                networkChipGroup.addView(chip)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
