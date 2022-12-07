package com.example.ugd3_c_kel9.daftarpaket

import com.example.ugd3_c_kel9.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    private const val BASE_URL = "http://4192.168.17.80ci4-apiserver-studeer/public/"
    val instances: api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(api::class.java)
    }
}