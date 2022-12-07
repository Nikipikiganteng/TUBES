package com.example.ugd3_c_kel9.users

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ugd3_c_kel9.ResponseCreate
import com.example.ugd3_c_kel9.users.RClientUser
import com.example.ugd3_c_kel9.databinding.ActivityFormEditUserBinding
import com.shashank.sony.fancytoastlib.FancyToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FormEditUserActivity  : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditUserBinding
    private var b: Bundle? = null
    private val listUsers = ArrayList<Users>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityFormEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Form Edit User"
        b = intent.extras
        val id = b?.getString("id")
        binding.tvEditTgl.setOnClickListener {
            val datePicker = DatePickerDialog.OnDateSetListener{
                    view, year, month, dayofMonth ->
                binding.tglEditView.text =
                    dateToString(year,month,dayofMonth)
            }
            dateDialog(this,datePicker).show()
        }
        id?.let { getDetailData(it) }

        binding.btnUpdate.setOnClickListener {
            with(binding) {
                val nama = txtEditNama.text.toString()
                val username = txtEditUsername.text.toString()
                val password = txtEditPassword.text.toString()
                val tgl_lahir = tvEditTgl.text.toString()
                val no_telpon = txtEditNotelpon.text.toString()
                val email = txtEditEmail.text.toString()

                RClientUser.instances.updateData(id,nama,username,password,tgl_lahir,no_telpon,email).enqueue(object :
                    Callback<ResponseCreate> {
                    override fun onResponse(
                        call: Call<ResponseCreate>,
                        response: Response<ResponseCreate>
                    ) {
                        if(response.isSuccessful) {
                            FancyToast.makeText(applicationContext,"Berhasil mengedit data",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,true).show()
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
    fun getDetailData(id:String) {
        RClientUser.instances.getData(id).enqueue(object : Callback<ResponseUsers> {
            override fun onResponse(
                call: Call<ResponseUsers>,
                response: Response<ResponseUsers>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        listUsers.addAll(it.data) }
                    with(binding) {
                        txtId.setText(listUsers[0].id)

                        txtEditNama.setText(listUsers[0].nama)

                        txtEditUsername.setText(listUsers[0].username)

                        txtEditPassword.setText(listUsers[0].password)

                        tvEditTgl.setText(listUsers[0].tgl_lahir)

                        txtEditNotelpon.setText(listUsers[0].no_telpon)

                        txtEditEmail.setText(listUsers[0].email)
                    }
                }}
            override fun onFailure(call:
                                   Call<ResponseUsers>, t: Throwable) {
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