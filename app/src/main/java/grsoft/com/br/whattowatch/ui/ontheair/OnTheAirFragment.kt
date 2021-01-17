package grsoft.com.br.whattowatch.ui.ontheair

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import grsoft.com.br.whattowatch.R

class OnTheAirFragment : Fragment() {

    companion object {
        fun newInstance() = OnTheAirFragment()
    }

    private lateinit var viewModel: OnTheAirViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.on_the_air_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OnTheAirViewModel::class.java)
        // TODO: Use the ViewModel
    }

}