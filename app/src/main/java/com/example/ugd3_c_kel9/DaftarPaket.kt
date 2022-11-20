package com.example.ugd3_c_kel9

import com.google.gson.annotations.SerializedName

data class DaftarPaket(
    @SerializedName("idbeli") val idbl:String,
    @SerializedName("namapelanggan") val nama:String,
    @SerializedName("paketbeli") val pkt:String,
    @SerializedName("masaperiode") val prd:String,
    @SerializedName("tglberlangganan") val tgllgn:String,
)