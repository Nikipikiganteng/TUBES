package com.example.ugd3_c_kel9.reservasiguru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.ugd3_c_kel9.R
import com.example.ugd3_c_kel9.daftarpaket.FormAddDaftarPaketActivity
import com.example.ugd3_c_kel9.databinding.ActivityDaftarPaketBinding
import com.example.ugd3_c_kel9.databinding.ActivityReservasiGuruBinding
import com.example.ugd3_c_kel9.fragment.DataDaftarPaketFragment
import com.example.ugd3_c_kel9.fragment.DataReservasiGuruFragment

class ReservasiGuruActivity : AppCompatActivity() {
    private lateinit var binding : ActivityReservasiGuruBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservasiGuruBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFragment()
        binding.txtCari.setOnKeyListener(View.OnKeyListener{ _, keyCode, event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action
                == KeyEvent.ACTION_UP)
            {
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })
        binding.btnAdd.setOnClickListener{
            startActivity(Intent(this, FormAddReservasiGuruActivity::class.java))
        }
    }
    fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = DataReservasiGuruFragment()
        val textCari = binding.txtCari.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }
}