package com.example.ugd3_c_kel9.reservasiguru

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ugd3_c_kel9.reservasiguru.ResponseCreateReservasiGuru
import com.example.ugd3_c_kel9.daftarpaket.RClient
import com.example.ugd3_c_kel9.databinding.ActivityFormAddDaftarPaketBinding
import com.example.ugd3_c_kel9.databinding.ActivityFormAddReservasiGuruBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormAddReservasiGuruActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddReservasiGuruBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddReservasiGuruBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            saveData()
        }
        binding.tvTgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view,year,month,dayofMonth ->
                binding.tglView.text =
                    dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
    }

    fun saveData(){
        with(binding) {
            val idrsv = txtIdrsv.text.toString()
            val nama= txtNama.text.toString()
            val status = txtStatus.text.toString()
            val tggl = tglView.text.toString()
            val wkt = txtWaktu.text.toString()

            RClientReservasiGuru.instances.createData(idrsv,nama,status,tggl,wkt).enqueue(object :
                Callback<ResponseCreateReservasiGuru> {
                override fun onResponse(
                    call: Call<ResponseCreateReservasiGuru>,
                    response: Response<ResponseCreateReservasiGuru>
                ) {
                    if(response.isSuccessful){
                        FancyToast.makeText(applicationContext,"Berhasil register",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,true).show()
                        finish()
                    }else {
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

                        txtIdrsv.setError(jsonObj.getString("message"))
                        FancyToast.makeText(applicationContext,"Maaf, akun sudah ada!",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()
                    }
                }
                override fun onFailure(call:
                                       Call<ResponseCreateReservasiGuru>, t: Throwable) {}
            })
        }
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