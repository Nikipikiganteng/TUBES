package com.example.ugd3_c_kel9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Username: String,
    val Email: String,
    val Password: String,
    val TanggalLahir: String,
    val NomorTelepon: String,
)