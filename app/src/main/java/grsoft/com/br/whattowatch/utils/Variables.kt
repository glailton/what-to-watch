package grsoft.com.br.whattowatch.utils

import timber.log.Timber
import kotlin.properties.Delegates

object Variables {
    var isNetworkConnected: Boolean by Delegates.observable(false) { _, _, newValue ->
        Timber.i("Network connectivity $newValue")
    }
}