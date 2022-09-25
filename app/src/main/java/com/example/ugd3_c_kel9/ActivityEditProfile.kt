package com.example.ugd3_c_kel9

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugd3_c_kel9.databinding.ActivityEditProfileBinding
import com.example.ugd3_c_kel9.room.User
import com.example.ugd3_c_kel9.room.UserDB
import com.example.ugd3_c_kel9.room.UserDao
import com.google.android.material.snackbar.Snackbar
import java.util.*


class ActivityEditProfile : AppCompatActivity() {
    val db by lazy { UserDB(this) }
    var itemBinding: ActivityEditProfileBinding? = null
    var sharedPreferences: SharedPreferences? = null
    private lateinit var editProfileLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemBinding = ActivityEditProfileBinding.inflate(layoutInflater)

        setContentView(itemBinding?.root)

        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE)
        val id = sharedPreferences?.getString("id", "")
        itemBinding?.Username?.setText(db?.UserDao()?.getUser(id!!.toInt())?.Username)
        itemBinding?.Email?.setText(db?.UserDao()?.getUser(id!!.toInt())?.Email)
        itemBinding?.TanggalLahir?.setText(db?.UserDao()?.getUser(id!!.toInt())?.TanggalLahir)
        itemBinding?.NomorTelepon?.setText(db?.UserDao()?.getUser(id!!.toInt())?.NomorTelepon)

        itemBinding?.TanggalLahir?.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    itemBinding?.etTanggalLahir?.editText?.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)

                },
                year,
                month,
                day
            )

            dpd.show()
        }


        itemBinding?.btnSave?.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            val Name: String = itemBinding?.etUsername?.editText?.getText().toString()
            val Email: String = itemBinding?.etEmail?.editText?.getText().toString()
            val BirthDate: String = itemBinding?.TanggalLahir?.getText().toString()
            val NoTelp: String = itemBinding?.etNomorTelepon?.editText?.getText().toString()

            var checkSave = true

            if (Name.isEmpty()) {
                itemBinding?.etUsername?.setError("Name must be filled with text")
                checkSave = false
            }

            if (NoTelp.isEmpty()) {
                itemBinding?.etNomorTelepon?.setError("Phone Number must be filled with text")
                checkSave = false
            }

            if (Email.isEmpty()) {
                itemBinding?.etEmail?.setError("E-mail must be filled with text")
                checkSave = false
            }

            if (!Email.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"))) {
                itemBinding?.etEmail?.setError("Email tidak valid")
                checkSave = false
            }

            if (BirthDate.isEmpty()) {
                itemBinding?.etTanggalLahir?.setError("Birth Date must be filled with text")
                checkSave = false
            }

            if (NoTelp.length != 12) {
                itemBinding?.etNomorTelepon?.setError("Phone Number length must be 12 digit")
                checkSave = false
            }

            if (checkSave == true) {
                setupListener()
                Toast.makeText(
                    applicationContext,
                    "Your Profile Changed",
                    Toast.LENGTH_SHORT
                ).show()
                val moveMenu = Intent(this, HomeActivity::class.java)
                startActivity(moveMenu)
            } else {
                return@OnClickListener
            }
        })
    }


    private fun setupListener() {
        sharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE)
        val id = sharedPreferences?.getString("id", "")

        db.UserDao().updateUser(
            User(
                id!!.toInt(),
                itemBinding?.Username?.getText().toString(),
                itemBinding?.Email?.text.toString(),
                db?.UserDao()?.getUser(id!!.toInt())?.Password.toString(),
                itemBinding?.TanggalLahir?.text.toString(),
                itemBinding?.NomorTelepon?.text.toString()
            )
        )
        finish()
    }

}