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
import com.example.ugd3_c_kel9.users.RClientUser
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.shashank.sony.fancytoastlib.FancyToast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val extras = Bundle()

//        setupHyperlink()

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

            RClientUser.instances.checkLogin(username, password).enqueue(object :
                Callback<ResponseCreate> {
                override fun onResponse(
                    call: Call<ResponseCreate>,
                    response: Response<ResponseCreate>
                ) {
                    if (response.isSuccessful) {
                        extras.putString("username", username)
                        extras.putString("password", password)
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        intent.putExtras(extras)
                        startActivity(intent)
                        FancyToast.makeText(
                            applicationContext, "${response.body()?.pesan}",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS, true
                        ).show()
                        finish()
                    } else {
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())

//                        txtUsername.setError(jsonObj.getString("message"))
                        inputUsername.setError("Username salah!")
                        inputPassword.setError("Password salah!")
                        FancyToast.makeText(
                            applicationContext, jsonObj.getString("message"),
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR, true
                        ).show()
                        //FancyToast.makeText(applicationContext,"Maaf sudah ada datanya",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show()
                    }
                }
                override fun onFailure(
                    call:
                    Call<ResponseCreate>, t: Throwable
                ) {
                }

            })
        })

//    fun setupHyperlink(){
//        val linkTextView = findViewById<TextView>(R.id.textViewRegister)
//        linkTextView.setMovementMethod(LinkMovementMethod.getInstance())
//
//        linkTextView.setOnClickListener(View.OnClickListener {
//            val movetoActivityRegister = Intent(this, RegisterActivity::class.java)
//            startActivity(movetoActivityRegister)
//        })
//    }
    }
}