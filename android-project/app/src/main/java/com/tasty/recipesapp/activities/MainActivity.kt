package com.tasty.recipesapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    companion object{
        private val TAG = MainActivity::class.java.simpleName ;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(bottomNavigationView , navController)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home_fragment -> navController.navigate(R.id.home_fragment)
                R.id.recipe_fragment -> navController.navigate(R.id.recipe_fragment)
                R.id.profile_fragment -> navController.navigate(R.id.profile_fragment)
                else -> {
                    navController.navigate(R.id.home_fragment)
                }
            }
            true

        }

        navController.addOnDestinationChangedListener{ _ , destination , _ ->
            if ( destination.id == R.id.fragment_login || destination.id == R.id.register_fragment) {
                bottomNavigationView.visibility = View.GONE
            }
            else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }

    }
    override fun onStart(){
        super.onStart() ;
        Log.d(TAG, "onStart: MainActivity started") ;
    }

    override fun onResume() {
        super.onResume();
        Log.d(TAG, "onResume: MainActivity resuming") ;
    }

    override fun onPause() {
        super.onPause() ;
        Log.d(TAG, "onPause: MainActivity paused") ;
    }

    override fun onStop() {
        super.onStop() ;
        Log.d(TAG, "onStop: MainActivity stopped") ;
    }

    override fun onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy MainActivity destroyed");
    }
}