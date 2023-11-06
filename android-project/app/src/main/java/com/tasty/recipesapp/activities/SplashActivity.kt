package com.tasty.recipesapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySplashBinding
    companion object{
        private val TAG = SplashActivity::class.java.simpleName
        private const val SPLASH_TIME_OUT:Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        Log.d(TAG , "onCreate: SplashActivity")
//        val textInput = binding.textInput
//        binding.buttonNavigate.setOnClickListener {
//            val intent = Intent(this@SplashActivity,MainActivity::class.java)
//            intent.putExtra("name" , textInput.text.toString())
//            startActivity(intent)
//        }
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root) ;

        val handlerThread = HandlerThread("SplashHandleThread" , -10) ;
        handlerThread.start() ;
        val handler = android.os.Handler(handlerThread.looper)
        handler.postDelayed({
            val intent = Intent(this@SplashActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
         } , SPLASH_TIME_OUT )


        }
    override fun onStart(){
        super.onStart() ;
        Log.d(TAG , "onStart: SplashActivity started") ;
    }

    override fun onResume() {
        super.onResume();
        Log.d(TAG , "onResume: SplashActivity resuming") ;
    }

    override fun onPause() {
        super.onPause() ;
        Log.d(TAG , "onPause: SplashActivity paused") ;
    }

    override fun onStop() {
        super.onStop() ;
        Log.d(TAG , "onStop: SplashActivity stopped") ;
    }

    override fun onDestroy() {
        super.onDestroy();
        Log.d(TAG , "onDestroy SplashActivity destroyed");
    }

    }