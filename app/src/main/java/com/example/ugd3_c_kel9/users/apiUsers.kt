package com.example.ugd3_c_kel9.users

import com.example.ugd3_c_kel9.ResponseCreate
import retrofit2.Call
import retrofit2.http.*

interface apiUsers {
    @GET("users/{cari}")
    fun getData(@Path("cari") cari:String? = null): Call<ResponseUsers>

    @FormUrlEncoded
    @POST("users")
    fun createData(
        @Field("id") idb:String?,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("tgl_lahir") tgl_lahir:String?,
        @Field("no_telpon") no_telpon:String?,
        @Field("email") email:String?,
    ): Call<ResponseCreate>
    @DELETE("users/{id}")
    fun deleteData(@Path("id")id: String?): Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("users/{id}")
    fun updateData(
        @Path("id") id:String?,
        @Field("nama") nama:String?,
        @Field("username") username:String?,
        @Field("password") password:String?,
        @Field("tgl_lahir") tgl_lahir:String?,
        @Field("no_telpon") no_telpon:String?,
        @Field("email") email:String?,
    ): Call<ResponseCreate>

    @FormUrlEncoded
    @POST("users/checkLogin")
    fun checkLogin(
        @Field("username") username:String?,
        @Field("password") password:String?
    ):Call<ResponseCreate>
}