package com.idm.onepiecelist.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.idm.onepiecelist.MainActivity
import com.idm.onepiecelist.R

class SplashActivity : AppCompatActivity() {
    companion object{
        const val TIME_SPLASH : Long = 1000;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME_SPLASH)
    }
}