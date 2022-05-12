package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Onboarding_timemgmt_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_timemgmt)
        var btnNext = findViewById<Button>(R.id.next_Btn_TimeMgmt)
        btnNext.setOnClickListener(){
            val intent = Intent(this, GetStartedActivity::class.java)
            startActivity(intent)
        }
    }
}