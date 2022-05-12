package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Onboarding_confused_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_confused_screen1)

        var btnNext = findViewById<Button>(R.id.next_btn_confused)
        btnNext.setOnClickListener(){
            val intent = Intent(this, Onboarding_timemgmt_Activity::class.java)
            startActivity(intent)
        }
    }
}