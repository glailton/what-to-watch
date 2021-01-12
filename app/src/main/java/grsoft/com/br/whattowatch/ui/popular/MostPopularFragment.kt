package grsoft.com.br.whattowatch.ui.popular

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import grsoft.com.br.whattowatch.R

class MostPopularFragment : Fragment() {

    companion object {
        fun newInstance() = MostPopularFragment()
    }

    private lateinit var viewModel: MostPopularViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.most_popular_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MostPopularViewModel::class.java)
        // TODO: Use the ViewModel
    }

}