package com.tasty.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.databinding.ActivitySplashBinding
import com.tasty.recipesapp.recipe.RecipeListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
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