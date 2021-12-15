package com.met.tech.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.met.tech.R

import androidx.appcompat.app.AppCompatActivity


class SplashActivity1 :  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash1)

        Handler().postDelayed({
            val intent = Intent(this, HomeActitvity::class.java)
            startActivity(intent)
        }, 8000)
    }

}

