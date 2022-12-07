package com.example.ugd3_c_kel9.daftarpaket

import com.example.ugd3_c_kel9.daftarpaket.DaftarPaket
import com.google.gson.annotations.SerializedName

data class ResponseDaftarPaket(
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<DaftarPaket>)