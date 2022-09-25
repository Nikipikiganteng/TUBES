package com.example.ugd3_c_kel9.entity

class Studeer(var name: String, var kelas: Int, var jurusan: String) {

    companion object{
        @JvmField
        var listOfStudeer = arrayOf(
            Studeer("Hans", 12, "IPA"),
            Studeer("Avila", 12, "IPA"),
            Studeer("Nicholas", 12, "IPA"),
            Studeer("Henry", 12, "IPA"),
            Studeer("Eric", 11, "IPS"),
            Studeer("Kevin", 11, "IPS"),
            Studeer("Leon", 10, "IPA"),
            Studeer("Rebecca", 10, "IPS"),
            Studeer("Vanessa", 11, "IPS"),
            Studeer("Via", 10, "IPA"),
        )
    }
}