package com.example.ugd3_c_kel9.reservasiguru

import com.google.gson.annotations.SerializedName

data class ReservasiGuru(
    @SerializedName("id_reservasi") val idrsv:String,
    @SerializedName("nama_guru") val nama:String,
    @SerializedName("status_reservasi") val status:String,
    @SerializedName("tanggal_reservasi") val tggl:String,
    @SerializedName("waktu_reservasi") val wkt:String,
)