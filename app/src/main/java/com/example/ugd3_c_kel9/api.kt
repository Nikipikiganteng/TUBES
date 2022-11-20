package com.example.ugd3_c_kel9

import retrofit2.Call
import retrofit2.http.*

interface api {
    @GET("daftarpaket/{cari}")
    fun getData(@Path("cari") cari:String? = null): Call<ResponseDaftarPaket>

    @FormUrlEncoded
    @POST("daftarpaket")
    fun createData(
        @Field("idbeli") idbeli:String?,
        @Field("namapelanggan") namapelanggan:String?,
        @Field("paketbeli") paketbeli:String?,
        @Field("masaperiode") masaperiode:String?,
        @Field("tglberlangganan") tglberlangganan:String?,
    ): Call<ResponseCreate>
    @DELETE("daftarpaket/{idbeli}")
    fun deleteData(@Path("idbeli")idbeli: String?): Call<ResponseCreate>
    @FormUrlEncoded
    @PUT("daftarpaket/{idbeli}")
    fun updateData(
        @Path("idbeli") idbeli:String?,
        @Field("namapelanggan") namapelanggan:String?,
        @Field("paketbeli") paketbeli:String?,
        @Field("masaperiode") masaperiode:String?,
        @Field("tglberlangganan") tglberlangganan:String?,
    ): Call<ResponseCreate>
}