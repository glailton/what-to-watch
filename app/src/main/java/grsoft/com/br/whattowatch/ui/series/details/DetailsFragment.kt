package grsoft.com.br.whattowatch.ui.series.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import grsoft.com.br.whattowatch.databinding.DetailsFragmentBinding
import grsoft.com.br.whattowatch.ui.series.details.about.AboutFragment
import grsoft.com.br.whattowatch.ui.series.details.adapters.ViewPageAdapter
import grsoft.com.br.whattowatch.ui.series.details.episodes.EpisodesFragment
import grsoft.com.br.whattowatch.utils.Resource

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val detailsViewModel: DetailsViewModel by viewModels()
    private var tvShowId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.id.let { detailsViewModel.start(it) }

        var viewPageAdapter = ViewPageAdapter(childFragmentManager)
        viewPageAdapter.addFragment(AboutFragment.newInstance(), "About")
        viewPageAdapter.addFragment(EpisodesFragment.newInstance(), "Episodes")
        binding.viewpager.adapter = viewPageAdapter
        binding.tabs.setupWithViewPager(binding.viewpager)

        setupObservers()
    }

    private fun setupObservers() {
        detailsViewModel.details.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    bindCharacter(it.data!!)
//                    binding.progressBar.visibility = View.GONE
//                    binding.characterCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.characterCl.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}