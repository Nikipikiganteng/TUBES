package com.example.ugd3_c_kel9.reservasiguru

import com.example.ugd3_c_kel9.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClientReservasiGuru {
    private const val BASE_URL = "http://192.168.100.154/ci4-apiserver-studeer/public/"
    val instances: apiReservasiGuru by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(apiReservasiGuru::class.java)
    }
}