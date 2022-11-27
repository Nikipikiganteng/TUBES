package com.example.ugd3_c_kel9

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ugd3_c_kel9.databinding.ActivityFormAddDaftarPaketBinding
import com.example.ugd3_c_kel9.databinding.ActivityFormEditDaftarPaketBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormAddDaftarPaketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddDaftarPaketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddDaftarPaketBinding.inflate(layoutInflater)
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
            val idbl = txtIdbl.text.toString()
            val nama= txtNama.text.toString()
            val pkt = txtPaket.text.toString()
            val prd = txtPeriode.text.toString()
            val lgn = tglView.text.toString()

            RClient.instances.createData(idbl,nama,pkt,prd,lgn).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){
                        FancyToast.makeText(applicationContext,"Berhasil menambah data",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                        finish()
                    }else {
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

                        txtIdbl.setError(jsonObj.getString("message"))
                        FancyToast.makeText(applicationContext,"Maaf, data sudah ada!",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,true).show()
                    }
                }
                override fun onFailure(call:
                                       Call<ResponseCreate>, t: Throwable) {}
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