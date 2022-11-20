package com.example.ugd3_c_kel9

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd3_c_kel9.databinding.ActivityFormEditDaftarPaketBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormEditDaftarPaketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditDaftarPaketBinding
    private var b: Bundle? = null
    private val listDaftarPaket = ArrayList<DaftarPaket>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditDaftarPaketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Daftar Paket"
        b = intent.extras
        val idbl = b?.getString("nim")
        binding.tvEditTgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view, year, month, dayofMonth ->
                binding.tglEditView.text =
                    dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
        idbl?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val pkt = txtEditPaket.text.toString()
                val prd = txtEditPeriode.text.toString()
                val tgllgn = tglEditView.text.toString()

                RClient.instances.updateData(idbl,nama,pkt,prd,tgllgn).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {
                            Toast.makeText(applicationContext,"${response.body()?.pesan}",
                                Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                    override fun onFailure(call:
                                           Call<ResponseCreate>, t: Throwable) {
                    }
                })
            }
        }
    }
    fun getDetailData(idbl:String) {
        RClient.instances.getData(idbl).enqueue(object : Callback<ResponseDaftarPaket> {
            override fun onResponse(
                call: Call<ResponseDaftarPaket>,
                response: Response<ResponseDaftarPaket>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listDaftarPaket.addAll(it.data) }
                    with(binding) {
                        txtIdbl.setText(listDaftarPaket[0].idbl)

                        txtEditNama.setText(listDaftarPaket[0].nama)

                        txtEditPaket.setText(listDaftarPaket[0].pkt)

                        txtEditPeriode.setText(listDaftarPaket[0].prd)

                        tglEditView.setText(listDaftarPaket[0].tgllgn)
                    }
                }}
            override fun onFailure(call:
                                   Call<ResponseDaftarPaket>, t: Throwable) {
            }
        })
    }
    private fun dateToString(year: Int, month: Int, dayofMonth:
    Int): String {
        return year.toString()+"-"+(month+1)+"-"+dayofMonth.toString()
    }
    private fun dateDialog(context: Context, datePicker:
    DatePickerDialog.OnDateSetListener): DatePickerDialog {
        val calender = Calendar.getInstance()
        return DatePickerDialog(
            context, datePicker,
            calender[Calendar.YEAR],
            calender[Calendar.MONTH],
            calender[Calendar.DAY_OF_MONTH],
        )
    }
}