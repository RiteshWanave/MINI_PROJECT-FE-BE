package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GetStartedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_getstarted)

        var btnNext = findViewById<Button>(R.id.next_btn_getstarted)
        btnNext.setOnClickListener(){
            val intent = Intent(this, welcome_Activity::class.java)
            startActivity(intent)
        }

    }
}