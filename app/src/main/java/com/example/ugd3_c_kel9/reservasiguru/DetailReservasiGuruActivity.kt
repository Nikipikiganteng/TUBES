package com.example.ugd3_c_kel9.reservasiguru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ugd3_c_kel9.ResponseCreate
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.FormEditReservasiGuruActivity
import com.example.ugd3_c_kel9.reservasiguru.RClientReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ResponseReservasiGuru
import com.example.ugd3_c_kel9.databinding.ActivityDetailReservasiGuruBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailReservasiGuruActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailReservasiGuruBinding
    private var b: Bundle? = null
    private val listReservasiGuru = ArrayList<ReservasiGuru>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailReservasiGuruBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b = intent.extras
        val idrsv = b?.getString("idrsv")
        idrsv?.let { getDataDetail(it) }
        binding.btnHapus.setOnClickListener {
            idrsv?.let { it1 -> deleteData(it1) }
        }
        binding.btnEdit.setOnClickListener {
            startActivity(
                Intent(this,
                    FormEditReservasiGuruActivity::class.java).apply {
                    putExtra("idrsv",idrsv)
                })
        }
    }
    fun getDataDetail(idrsv:String){
        RClientReservasiGuru.instances.getData(idrsv).enqueue(object : Callback<ResponseReservasiGuru> {
            override fun onResponse(
                call: Call<ResponseReservasiGuru>,
                response: Response<ResponseReservasiGuru>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listReservasiGuru.addAll(it.data) }
                    with(binding) {
                        tvIdrsv.text = listReservasiGuru[0].idrsv
                        tvNama.text = listReservasiGuru[0].nama
                        tvStatus.text = listReservasiGuru[0].status
                        tvTggl.text = listReservasiGuru[0].tggl
                        tvWaktu.text = listReservasiGuru[0].wkt
                    }
                }
            }
            override fun onFailure(call: Call<ResponseReservasiGuru>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(idrsv:String){
        val builder = AlertDialog.Builder(this@DetailReservasiGuruActivity)
        builder.setMessage("Anda Yakin mau hapus?? Saya ngga yakin loh.")
            .setCancelable(false)
            .setPositiveButton("Ya, Hapus Aja!"){dialog, id->
                doDeleteData(idrsv)
            }
            .setNegativeButton("Tidak, Masih sayang dataku"){dialog,id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    private fun doDeleteData(idrsv:String) {
        RClientReservasiGuru.instances.deleteData(idrsv).enqueue(object :
            Callback<ResponseCreateReservasiGuru> {
            override fun onResponse(
                call: Call<ResponseCreateReservasiGuru>,
                response: Response<ResponseCreateReservasiGuru>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            override fun onFailure(call: Call<ResponseCreateReservasiGuru>, t: Throwable) {
            }
        })
    }
}