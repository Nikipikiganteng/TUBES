package com.example.ugd3_c_kel9.reservasiguru

import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.google.gson.annotations.SerializedName

data class ResponseReservasiGuru(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<ReservasiGuru>)