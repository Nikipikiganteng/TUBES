package com.example.ugd3_c_kel9.room


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pembelian (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: String,
    val activity: String,
)