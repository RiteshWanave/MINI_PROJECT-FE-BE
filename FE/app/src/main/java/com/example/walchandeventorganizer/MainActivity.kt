package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    //delay time
    private val splashScreenTime : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //to hide actionBar
        supportActionBar?.hide()

        //animation
        val background: ImageView = findViewById(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this,R.anim.fade)
        background.startAnimation(animation)

        //delay between splash and onBoarding screen
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this,Onboarding_confused_Screen::class.java))
            finish()
        },splashScreenTime)

    }
}
