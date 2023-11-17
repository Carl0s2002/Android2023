package com.tasty.recipesapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.viewModel.RecipeListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController
    companion object{
        private val TAG = MainActivity::class.java.simpleName ;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        Log.d(TAG , "onCreate: MainActivity created.") ;
//        val view = ActivityMainBinding.inflate(layoutInflater) ;
//        setContentView(view.root) ;
//        val name = intent.getStringExtra("name") ;
//        view.textGreeting.text = "Hello $name" ;

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        replaceFragment(HomeFragment()) ;

//        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
//            val menuItemId = item.itemId
//            when(item.itemId) {
//                R.id.home_fragment -> replaceFragment(HomeFragment())
//                R.id.recipe_fragment -> replaceFragment(RecipeFragment())
//                R.id.profile_fragment -> replaceFragment(ProfileFragment())
//            }
//            true
//        })

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
//        val recipes = RecipeRepository(this).readRecipes()
//        recipes.forEach {
//            Log.d(TAG , "recipe: $it")
//        }

        val viewModel: RecipeListViewModel by viewModels()
        val liveData = viewModel.liveData
        liveData.observe(this) {
            it.forEach {
                Log.d(TAG , it.toString() )
            }
        }
        viewModel.readAllRecipes(this)
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }
}