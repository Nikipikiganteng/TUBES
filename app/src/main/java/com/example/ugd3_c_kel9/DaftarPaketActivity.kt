package com.example.ugd3_c_kel9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.ugd3_c_kel9.databinding.ActivityDaftarPaketBinding
import com.example.ugd3_c_kel9.fragment.DaftarPaketFragment
import com.example.ugd3_c_kel9.fragment.DataDaftarPaketFragment

class DaftarPaketActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDaftarPaketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarPaketBinding.inflate(layoutInflater)
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
            startActivity(Intent(this, FormAddDaftarPaketActivity::class.java))
        }
    }
    fun showDataFragment() {
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction =
            mFragmentManager.beginTransaction()
        val mFragment = DataDaftarPaketFragment()
        val textCari = binding.txtCari.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle
        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }
}