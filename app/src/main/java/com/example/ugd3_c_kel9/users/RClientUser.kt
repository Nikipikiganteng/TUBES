package com.example.ugd3_c_kel9.users

import com.example.ugd3_c_kel9.users.apiUsers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClientUser {
    private const val BASE_URL = "http://192.168.100.154:8081/ci4-apiserver-studeer/public/"
    val instances: apiUsers by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(apiUsers::class.java)
    }
}