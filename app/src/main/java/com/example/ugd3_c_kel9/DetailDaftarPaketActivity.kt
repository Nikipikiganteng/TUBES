package com.example.ugd3_c_kel9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ugd3_c_kel9.databinding.ActivityDetailDaftarPaketBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailDaftarPaketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailDaftarPaketBinding
    private var b: Bundle? = null
    private val listDaftarPaket = ArrayList<DaftarPaket>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetailDaftarPaketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        b = intent.extras
        val idbl = b?.getString("idbl")
        idbl?.let { getDataDetail(it) }
        binding.btnHapus.setOnClickListener {
            idbl?.let { it1 -> deleteData(it1) }
        }
        binding.btnEdit.setOnClickListener {
            startActivity(
                Intent(this,
                    FormEditDaftarPaketActivity::class.java).apply {
                    putExtra("idbl",idbl)
                })
        }
    }
    fun getDataDetail(idbl:String){
        RClient.instances.getData(idbl).enqueue(object : Callback<ResponseDaftarPaket> {
            override fun onResponse(
                call: Call<ResponseDaftarPaket>,
                response: Response<ResponseDaftarPaket>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listDaftarPaket.addAll(it.data) }
                    with(binding) {
                        tvIdbl.text = listDaftarPaket[0].idbl
                        tvNama.text = listDaftarPaket[0].nama
                        tvPaket.text = listDaftarPaket[0].pkt
                        tvPeriode.text = listDaftarPaket[0].prd
                        tvTgllgn.text = listDaftarPaket[0].tgllgn
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDaftarPaket>, t: Throwable) {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }
    fun deleteData(idbl:String){
        val builder = AlertDialog.Builder(this@DetailDaftarPaketActivity)
        builder.setMessage("Anda Yakin mau hapus?? Saya ngga yakin loh.")
            .setCancelable(false)
            .setPositiveButton("Ya, Hapus Aja!"){dialog, id->
                doDeleteData(idbl)
            }
            .setNegativeButton("Tidak, Masih sayang dataku"){dialog,id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
    private fun doDeleteData(idbl:String) {
        RClient.instances.deleteData(idbl).enqueue(object :
            Callback<ResponseCreate> {
            override fun onResponse(
                call: Call<ResponseCreate>,
                response: Response<ResponseCreate>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
            override fun onFailure(call: Call<ResponseCreate>, t: Throwable) {
            }
        })
    }
}