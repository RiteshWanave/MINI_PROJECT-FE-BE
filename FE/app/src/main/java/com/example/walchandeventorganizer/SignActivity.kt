package com.example.walchandeventorganizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import com.example.login1.databinding.ActivityMainBinding



class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.signup_screen)
        val Intent = intent
        val editemail= findViewById<TextView>(R.id.signup_email)
        val editname= findViewById<TextView>(R.id.signup_name)
        val editPassword = findViewById<TextView>(R.id.signup_Password)
        val btn_signup = findViewById<Button>(R.id.btn_signup)

        fun signup(email: String, name: String, password: String){
            val retIn = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
            val registerInfo = UserBody(email,name,password)

            retIn.registerUser(registerInfo).enqueue(object :
                Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        this@SignActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.code() == 200) {
                        Toast.makeText(this@SignActivity, "Registration success!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else{
                        Toast.makeText(this@SignActivity, "Registration failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }

        btn_signup.setOnClickListener{
            signup(email = editemail.text.toString(), name = editname.text.toString(), password = editPassword.text.toString())
        }


    }
}