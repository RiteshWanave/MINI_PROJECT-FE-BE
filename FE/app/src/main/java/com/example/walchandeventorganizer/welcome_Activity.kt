package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class welcome_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val intent = Intent(this, LoginActivity::class.java)
        val Intent = Intent(this, SignActivity::class.java)

        val login=findViewById<Button>(R.id.login)
        val signup=findViewById<Button>(R.id.signup)

        login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signup.setOnClickListener {
            startActivity(Intent(this, SignActivity::class.java))

            val intent= Intent(this,LoginActivity::class.java)
            val Intent= Intent(this,SignActivity::class.java)

        }
    }
}