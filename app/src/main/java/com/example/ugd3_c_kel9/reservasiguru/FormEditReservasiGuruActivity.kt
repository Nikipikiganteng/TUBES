package com.example.ugd3_c_kel9.reservasiguru

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd3_c_kel9.reservasiguru.ResponseCreateReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.RClientReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ResponseReservasiGuru
import com.example.ugd3_c_kel9.databinding.ActivityFormEditReservasiGuruBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormEditReservasiGuruActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditReservasiGuruBinding
    private var b: Bundle? = null
    private val listReservasiGuru = ArrayList<ReservasiGuru>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditReservasiGuruBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit Reservasi Guru"
        b = intent.extras
        val idrsv = b?.getString("idrsv")
        binding.tvEditTgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view, year, month, dayofMonth ->
                binding.tglEditView.text =
                    dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
        idrsv?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val status = txtEditStatus.text.toString()
                val tggl = tglEditView.text.toString()
                val wkt = txtEditWaktu.text.toString()

                RClientReservasiGuru.instances.updateData(idrsv,nama,status,tggl,wkt).enqueue(object :
                    Callback<ResponseCreateReservasiGuru> {
                    override fun onResponse(
                        call: Call<ResponseCreateReservasiGuru>,
                        response: Response<ResponseCreateReservasiGuru>
                    ) {
                        if(response.isSuccessful) {
                            FancyToast.makeText(applicationContext,"Berhasil mengedit data",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,true).show()
                            finish()
                        }
                    }
                    override fun onFailure(call:
                                           Call<ResponseCreateReservasiGuru>, t: Throwable) {
                    }
                })
            }
        }
    }
    fun getDetailData(idrsv:String) {
        RClientReservasiGuru.instances.getData(idrsv).enqueue(object :
            Callback<ResponseReservasiGuru> {
            override fun onResponse(
                call: Call<ResponseReservasiGuru>,
                response: Response<ResponseReservasiGuru>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listReservasiGuru.addAll(it.data) }
                    with(binding) {
                        txtIdrsv.setText(listReservasiGuru[0].idrsv)

                        txtEditNama.setText(listReservasiGuru[0].nama)

                        txtEditStatus.setText(listReservasiGuru[0].status)

                        tglEditView.setText(listReservasiGuru[0].tggl)

                        txtEditWaktu.setText(listReservasiGuru[0].wkt)
                    }
                }}
            override fun onFailure(call:
                                   Call<ResponseReservasiGuru>, t: Throwable) {
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