package com.idm.onepiecelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.idm.onepiecelist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.detailFragment -> {
                    binding.botNav.visibility = View.GONE
                    binding.imageView.visibility = View.GONE
                }
                else -> {
                    binding.botNav.visibility = View.VISIBLE
                    binding.imageView.visibility = View.VISIBLE
                }
            }
        }
        NavigationUI.setupWithNavController(binding.botNav,navController)
    }
}