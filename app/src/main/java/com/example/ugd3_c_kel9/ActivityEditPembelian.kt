package com.example.ugd3_c_kel9

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.ugd3_c_kel9.room.Constant
import com.example.ugd3_c_kel9.room.UserDB
import com.example.ugd3_c_kel9.room.Pembelian
import kotlinx.android.synthetic.main.activity_edit_pembelian.*
import kotlinx.android.synthetic.main.fragment_pembelian.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class

ActivityEditPembelian : AppCompatActivity() {
    val db by lazy { UserDB(this) }

    private val CHANNEL_ID = "channel_notification"
    private val notificationId = 100

    private var pembelianId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pembelian)

        setupView()
        setupListener()
    }

    fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {
                btnUpdate.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                btnSave.visibility = View.GONE
                btnUpdate.visibility = View.GONE
                getNote()
            }
            Constant.TYPE_UPDATE -> {
                btnSave.visibility = View.GONE
                getNote()
            }
        }
    }
    private fun setupListener() {
        btnSave.setOnClickListener {
            db.pembelianDao().addPembelian(
                Pembelian(0,etTitle.text.toString(), etDate.text.toString(), etActivity.text.toString())
            )
            sendNotificationSave()
            finish()
        }
        btnUpdate.setOnClickListener {
            db.pembelianDao().updatePembelian(
                Pembelian(pembelianId,etTitle.text.toString(), etDate.text.toString(), etActivity.text.toString())
            )
            sendNotificationSave()
            finish()
        }
    }

    fun getNote() {
        pembelianId = intent.getIntExtra("intent_id", 0)

        val notes = db.pembelianDao().getpembelian(pembelianId)
        etTitle.setText(notes.title)
        etDate.setText(notes.date)
        etActivity.setText(notes.activity)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun sendNotificationSave(){
        val bigtext = etActivity.getText().toString()
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logostudeer)
            .setContentTitle("Paket Ditambah")
            .setContentText(etTitle.getText().toString() + "\n" + etDate.getText().toString())
            .setColor(Color.GREEN)
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText(bigtext))
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }

    }

    private fun sendNotificationEdit(){
        val bigtext = etActivity.getText().toString() + "\n" + etDate.getText().toString()
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logostudeer)
            .setContentTitle("Paket Diedit")
            .setContentText(etTitle.getText().toString())
            .setColor(Color.GREEN)
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText(bigtext))
        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())

        }

    }
}