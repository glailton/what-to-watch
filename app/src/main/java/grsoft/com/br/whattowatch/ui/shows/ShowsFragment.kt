package grsoft.com.br.whattowatch.ui.shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.databinding.ShowsFragmentBinding

@AndroidEntryPoint
class ShowsFragment : Fragment() {

    private var _binding: ShowsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = ShowsFragmentBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}