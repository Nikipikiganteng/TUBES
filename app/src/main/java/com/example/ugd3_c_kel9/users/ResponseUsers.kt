package com.example.ugd3_c_kel9.users

import com.example.ugd3_c_kel9.users.Users
import com.google.gson.annotations.SerializedName

data class ResponseUsers (
    @SerializedName("status") val stt:String,
    val totaldata: Int,
    val data:List<Users>)