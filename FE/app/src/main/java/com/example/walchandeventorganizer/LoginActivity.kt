package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)
        val intent = intent
        val editemail= findViewById<TextView>(R.id.login_email)
        val editPassword = findViewById<TextView>(R.id.login_Password)
        val btn_login = findViewById<Button>(R.id.btn_login)

        fun signin(email: String, password: String){
            val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val signInInfo = SignInBody(email, password)
            retIn.signin(signInInfo).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@LoginActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.code() == 200) {
                        Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        btn_login.setOnClickListener{
            signin(email = editemail.text.toString(), password = editPassword.text.toString())
        }

    }
}