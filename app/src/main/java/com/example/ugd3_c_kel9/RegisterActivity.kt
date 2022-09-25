package com.example.ugd3_c_kel9

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.example.ugd3_c_kel9.databinding.ActivityRegisterBinding
import com.example.ugd3_c_kel9.room.User
import com.example.ugd3_c_kel9.room.UserDB
import com.example.ugd3_c_kel9.room.UserDao
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class RegisterActivity : AppCompatActivity() {
    private lateinit var itemBinding: ActivityRegisterBinding

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputEmail: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var inputTanggalLahir: TextInputLayout
    private lateinit var inputNomorTelepon: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        itemBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = itemBinding.root
        setContentView(view)

        itemBinding.TanggalLahir.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    // Display Selected date in textbox
                    itemBinding.TanggalLahir.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)

                }, year, month, day)

            dpd.show()
        }

        itemBinding.btnRegister.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            val mbundle = Bundle()

            val Name: String = itemBinding.inputLayoutUsername.editText?.getText().toString()
            val Email: String = itemBinding.inputLayoutEmail.editText?.getText().toString()
            val Password: String = itemBinding.inputLayoutPassword.editText?.getText().toString()
            val TanggalLahir: String = itemBinding.inputLayoutTanggalLahir.editText?.getText().toString()
            val NomorTelepon: String = itemBinding.inputLayoutNomorTelepon.editText?.getText().toString()


            var checkSignUp = true

            if (Name.isEmpty()) {
                itemBinding.inputLayoutUsername.setError("Name must be filled with text")
                checkSignUp = false
            }

            if (NomorTelepon.isEmpty()) {
                itemBinding.inputLayoutNomorTelepon.setError("Phone Number must be filled with text")
                checkSignUp = false
            }

            if (Email.isEmpty()) {
                itemBinding.inputLayoutEmail.setError("E-mail must be filled with text")
                checkSignUp = false
            }

            if (!Email.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"))) {
                itemBinding.inputLayoutEmail.setError("Email tidak valid")
                checkSignUp = false
            }

            if (TanggalLahir.isEmpty()) {
                itemBinding.inputLayoutEmail.setError("Birth Date must be filled with text")
                checkSignUp = false
            }

            if (Password.isEmpty()) {
                itemBinding.inputLayoutPassword.setError("Password must be filled with text")
                checkSignUp = false
            }

            if (NomorTelepon.length != 12) {
                itemBinding.inputLayoutNomorTelepon.setError("Phone Number length must be 12 digit")
                checkSignUp = false
            }

                if (checkSignUp == true) {
                    // simpan data ke database
                    val db by lazy { UserDB(this) }
                    val userDao = db.UserDao()

                    val user = User(0, Name, Email, Password, TanggalLahir, NomorTelepon)
                    userDao.addUser(user)

                    val movetoLogin = Intent(this, MainActivity::class.java)
                    val bundle: Bundle = Bundle()
                    bundle.putString("username", Name)
                    bundle.putString("email", Email)
                    bundle.putString("password", Password)
                    bundle.putString("Tanggallahir", TanggalLahir)
                    bundle.putString("NoHandphone", NomorTelepon)
                    movetoLogin.putExtra("register", bundle)
                    startActivity(movetoLogin)

                } else {
                    return@OnClickListener
                }

            })
        itemBinding.textViewLogin.setOnClickListener(View.OnClickListener {
            val home = Intent(this, MainActivity::class.java)
            startActivity(home)
        })
    }
    }




