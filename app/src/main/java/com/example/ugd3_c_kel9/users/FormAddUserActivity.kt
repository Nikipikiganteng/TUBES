package com.example.ugd3_c_kel9.users

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ugd3_c_kel9.ResponseCreate
import com.example.ugd3_c_kel9.users.RClientUser
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.example.ugd3_c_kel9.databinding.ActivityFormAddUserBinding

class FormAddUserActivity  : AppCompatActivity() {
    private lateinit var binding : ActivityFormAddUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormAddUserBinding.inflate(layoutInflater)
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
            val nama= txtNama.text.toString()
            val username = txtUsername.text.toString()
            val password = txtPassword.text.toString()
            val tgl_lahir = tglView.text.toString()
            val no_telpon = txtNotelpon.text.toString()
            val email = txtEmail.text.toString()

            RClientUser.instances.createData(nama,username,password,tgl_lahir,no_telpon,email).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if(response.isSuccessful){
                        FancyToast.makeText(applicationContext,"${response.body()?.pesan}",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        FancyToast.makeText(applicationContext,jsonObj.getString("message"),
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