package grsoft.com.br.whattowatch.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


fun Fragment.getViewModelProvider() =
        activity?.let(ViewModelProviders::of) ?: ViewModelProviders.of(this)

inline fun <reified T : ViewModel> Fragment.getViewModel() =
        getViewModelProvider().get(T::class.java)