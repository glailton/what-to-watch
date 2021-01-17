package grsoft.com.br.whattowatch.ui.rated

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import grsoft.com.br.whattowatch.R

class TopRatedFragment : Fragment() {

    companion object {
        fun newInstance() = TopRatedFragment()
    }

    private lateinit var viewModel: TopRatedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_rated_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}