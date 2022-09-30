package com.example.ugd3_c_kel9

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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
    private val notificationId = 101
    private val CHANNEL_ID = "channel_notification"

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
                    createNotificationChannel()
                    sendNotification()
                    Toast.makeText(applicationContext, itemBinding?.Username?.getText().toString() + " registered", Toast.LENGTH_SHORT).show()
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
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT).apply{
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel1)
        }
    }

    private fun sendNotification(){
        val intent: Intent = Intent(this, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val broadcastIntent: Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", "Login first to access the class")
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.logostudeer)
        val bigPictureStyle = NotificationCompat.BigPictureStyle()
            .bigPicture(largeIcon)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logostudeer)
            .setStyle(bigPictureStyle)
            .setContentTitle("Register Success")
            .setContentText("Thankyou for being a member in the Studeer")
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.GREEN)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "Access Class", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        Toast.makeText(applicationContext, "Register with your new Account first", Toast.LENGTH_SHORT).show()
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }
    }




