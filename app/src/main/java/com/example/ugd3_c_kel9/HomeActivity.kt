package com.example.ugd3_c_kel9

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ugd3_c_kel9.fragment.DaftarPaketFragment
import com.example.ugd3_c_kel9.fragment.ReservasiGuruFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = HomeFragment()
        val fragmentProfile = ProfileFragment()
        val fragmentPembelian = PembelianFragment()
        val fragmentDaftarPaket = DaftarPaketFragment()
        val fragmentReservasiGuru = ReservasiGuruFragment()

        setCurrentFragment(fragmentHome)

        val btmNav: BottomNavigationView = findViewById(R.id.bottomNavigation)

        btmNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.itemHome -> {
                    setCurrentFragment(fragmentHome)
                    true
                }
                R.id.itemPembelian -> {
                    setCurrentFragment(fragmentPembelian)
                    true
                }
                R.id.itemProfile -> {
                    setCurrentFragment(fragmentProfile)
                    true
                }
                R.id.itemDaftarPaket -> {
                    setCurrentFragment(fragmentDaftarPaket)
                    true
                }
                R.id.itemReservasiGuru -> {
                    setCurrentFragment(fragmentReservasiGuru)
                    true
                }
                else -> false
            }
        }
    }

    fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container,fragment)
            commit()
        }

    fun setActivity(activity: AppCompatActivity){
        val moveActivity = Intent(this, activity::class.java)
        startActivity(moveActivity)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_exit){

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
            builder.setMessage("Yakin ingin keluar?")
                .setPositiveButton("YA!", object : DialogInterface.OnClickListener{
                    override fun onClick(dialogInterface: DialogInterface, i: Int){
                        finishAndRemoveTask()
                    }
                })
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}