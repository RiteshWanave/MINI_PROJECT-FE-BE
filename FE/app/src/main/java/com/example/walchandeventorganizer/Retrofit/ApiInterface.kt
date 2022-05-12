package com.example.walchandeventorganizer

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type:application/json")
    @POST("login")
    fun signin(@Body info: SignInBody): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("users")
    fun registerUser(
        @Body info: UserBody
    ): retrofit2.Call<ResponseBody>
}

class RetrofitInstance {
    companion object {
        private const val BASE_URL: String = "http://10.0.2.2:3000"

//        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//            this.level = HttpLoggingInterceptor.Level.BODY
//        }
//        private val client: OkHttpClient = OkHttpClient.Builder().apply {
//            this.addInterceptor(interceptor)
//        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}


data class UserBody(val email: String,
                    val name: String,
                    val password: String)

data class SignInBody(val email: String, val password: String)