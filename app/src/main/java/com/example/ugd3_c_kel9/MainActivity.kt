package com.example.ugd3_c_kel9

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ugd3_c_kel9.room.User
import com.example.ugd3_c_kel9.room.UserDB
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.shashank.sony.fancytoastlib.FancyToast

class MainActivity : AppCompatActivity() {
    val db by lazy{ UserDB(this) }
    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout

    private val id = "id"
    private var access = false
    var sharedPreferences:SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupHyperlink()

        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputPassword = findViewById(R.id.inputLayoutPassword)
        mainLayout = findViewById(R.id.mainLayout)

        val btnClear: Button
        val btnLogin: Button

        btnClear = findViewById(R.id.btnClear)
        btnLogin = findViewById(R.id.btnLogin)

        btnClear.setOnClickListener {
            inputUsername.editText?.setText("")
            inputPassword.editText?.setText("")

            Snackbar.make(mainLayout, "Text Cleared Success", Snackbar.LENGTH_LONG).show()
        }

        btnLogin.setOnClickListener(View.OnClickListener {

            val username: String = inputUsername.editText?.getText().toString()
            val password: String = inputPassword.editText?.getText().toString()

            if(username.isEmpty()){
                inputUsername.setError("Username must be filled with text")
            }

            if(password.isEmpty()){
                inputPassword.setError("Password must be filled with text")
            }

            inputUsername = findViewById(R.id.inputLayoutUsername)
            inputPassword = findViewById(R.id.inputLayoutPassword)
            val userDB: User? = db.UserDao().getLogin(inputUsername.editText?.getText().toString(), inputPassword.editText?.getText().toString())

            if (userDB != null) {
                sharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE)
                var editor = sharedPreferences?.edit()
                editor?.putString("id", userDB.id.toString())
                editor?.commit()
                val moveMenu = Intent(this, HomeActivity::class.java)
                FancyToast.makeText(this,"Berhasil Login",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,true).show()
                startActivity(moveMenu)
            } else {
                Snackbar.make(
                    mainLayout,
                    "Username or Password incorrect",
                    Snackbar.LENGTH_LONG
                ).show()
                return@OnClickListener
            }
        })
    }

    fun setupHyperlink(){
        val linkTextView = findViewById<TextView>(R.id.textViewRegister)
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance())

        linkTextView.setOnClickListener(View.OnClickListener {
            val movetoActivityRegister = Intent(this, RegisterActivity::class.java)
            startActivity(movetoActivityRegister)
        })
    }
}