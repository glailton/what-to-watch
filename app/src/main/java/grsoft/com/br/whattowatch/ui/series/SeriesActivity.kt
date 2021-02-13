package grsoft.com.br.whattowatch.ui.series

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import grsoft.com.br.whattowatch.R
import grsoft.com.br.whattowatch.databinding.ActivitySeriesBinding

@AndroidEntryPoint
class SeriesActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivitySeriesBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

//        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
//            this,
//            binding.mainDrawer,
//            binding.toolbar,
//            R.string.drawer_open,
//            R.string.drawer_close
//        ){
//            override fun onDrawerClosed(view: View){
//                super.onDrawerClosed(view)
//            }
//
//            override fun onDrawerOpened(drawerView: View){
//                super.onDrawerOpened(drawerView)
//            }
//        }
//
//        drawerToggle.isDrawerIndicatorEnabled = true
//        binding.mainDrawer.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()

        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.detailsFragment) {
                binding.bottomNav.visibility = View.GONE
                binding.toolbar.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                binding.toolbar.visibility = View.VISIBLE
            }
        }

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.popularFragment,
            R.id.showsFragment,
            R.id.watchFragment,
            R.id.topRatedFragment,
            R.id.onTheAirFragment
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}