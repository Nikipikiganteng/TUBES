package com.example.ugd3_c_kel9.users

import com.google.gson.annotations.SerializedName

class Users (
    @SerializedName("id") val id:String,
    @SerializedName("nama") val nama:String,
    @SerializedName("username") val username:String,
    @SerializedName("password") val password:String,
    @SerializedName("tgl_lahir") val tgl_lahir:String,
    @SerializedName("no_telpon") val no_telpon:String,
    @SerializedName("email") val email:String,
    )