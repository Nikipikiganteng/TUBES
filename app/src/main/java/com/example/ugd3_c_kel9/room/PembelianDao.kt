package com.example.ugd3_c_kel9.room

import androidx.room.*

@Dao
interface PembelianDao {

    @Insert
    fun addPembelian(pembelian: Pembelian)

    @Update
    fun updatePembelian(pembelian: Pembelian)

    @Delete
    fun deletePembelian(pembelian: Pembelian)

    @Query("SELECT * FROM pembelian")
    fun getPembelian() : List<Pembelian>

    @Query("SELECT * FROM pembelian WHERE id =:Pembelian_id")
    fun getpembelian(Pembelian_id: Int) : Pembelian
}