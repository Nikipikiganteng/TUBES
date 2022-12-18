package com.example.ugd3_c_kel9.reservasiguru

import com.example.ugd3_c_kel9.reservasiguru.ResponseCreateReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ResponseReservasiGuru
import retrofit2.Call
import retrofit2.http.*

interface apiReservasiGuru {
    @GET("reservasiguru/{cari}")
    fun getData(@Path("cari") cari:String? = null): Call<ResponseReservasiGuru>

    @FormUrlEncoded
    @POST("reservasiguru")
    fun createData(
        @Field("id_reservasi") id_reservasi:String?,
        @Field("nama_guru") nama_guru:String?,
        @Field("status_reservasi") status_reservasi:String?,
        @Field("tanggal_reservasi") tanggal_reservasi:String?,
        @Field("waktu_reservasi") waktu_reservasi:String?,
    ): Call<ResponseCreateReservasiGuru>
    @DELETE("reservasiguru/{id_reservasi}")
    fun deleteData(@Path("id_reservasi")id_reservasi: String?): Call<ResponseCreateReservasiGuru>
    @FormUrlEncoded
    @PUT("reservasiguru/{id_reservasi}")
    fun updateData(
        @Path("id_reservasi") id_reservasi:String?,
        @Field("nama_guru") nama_guru:String?,
        @Field("status_reservasi") status_reservasi:String?,
        @Field("tanggal_reservasi") tanggal_reservasi:String?,
        @Field("waktu_reservasi") waktu_reservasi:String?,
    ): Call<ResponseCreateReservasiGuru>
}