package grsoft.com.br.whattowatch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import grsoft.com.br.whattowatch.utils.NetworkMonitor
import timber.log.Timber

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkMonitor(this).startNetWorkCallback()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        NetworkMonitor(this).stopNetworkCallback()
    }
}