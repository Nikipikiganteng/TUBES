package com.example.ugd3_c_kel9.reservasiguru

import com.google.gson.annotations.SerializedName

class ResponseCreateReservasiGuru (
    @SerializedName("status") val stt:Int,
    @SerializedName("error") val e:Boolean,
    @SerializedName("message") val pesan:String,
)