package grsoft.com.br.whattowatch.ui.series.details.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.data.entities.Details
import grsoft.com.br.whattowatch.databinding.AboutFragmentBinding
import grsoft.com.br.whattowatch.databinding.DetailsFragmentBinding

class AboutFragment : Fragment() {

    private lateinit var details: Details
    private var _binding: AboutFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(details: Details) = AboutFragment().apply {
            arguments = Bundle().apply {
                putParcelable("details", details)
            }
        }
    }

    private lateinit var viewModel: AboutViewModel

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
            details = it
            binding.textViewDescription.text = details.overview
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}