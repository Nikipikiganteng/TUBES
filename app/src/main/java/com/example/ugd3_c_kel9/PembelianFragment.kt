package com.example.ugd3_c_kel9

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd3_c_kel9.room.Constant
import com.example.ugd3_c_kel9.room.UserDB
import com.example.ugd3_c_kel9.room.Pembelian
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pembelian.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PembelianFragment : Fragment() {
    val db by lazy{activity?.let { UserDB(it ) }  }
    lateinit var pembelianAdapter: PembelianAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pembelian, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupRecyclerView()
        loadData()
    }

    private fun setupRecyclerView() {
        pembelianAdapter = PembelianAdapter(arrayListOf(), object :
            PembelianAdapter.OnAdapterListener{
            override fun onClick(pembelian: Pembelian) {
                intentEdit(pembelian.id,Constant.TYPE_READ)
            }
            override fun onUpdate(pembelian: Pembelian) {
                intentEdit(pembelian.id, Constant.TYPE_UPDATE)
            }
            override fun onDelete(pembelian: Pembelian) {
                deleteDialog(pembelian)
            }
        })
        list_schedule.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pembelianAdapter
        }
    }

    private fun deleteDialog(pembelian: Pembelian){
        val alertDialog = activity?.let { AlertDialog.Builder(it) }
        alertDialog?.apply {
            setTitle("Confirmation")
            setMessage("Are You Sure to delete this data From ${pembelian.title}?")
            setNegativeButton("Cancel", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            setPositiveButton("Delete", DialogInterface.OnClickListener
            { dialogInterface, i ->
                dialogInterface.dismiss()
                db?.pembelianDao()?.deletePembelian(pembelian)
                loadData()

            })
        }
        alertDialog?.show()
    }
    override fun onStart() {
        super.onStart()
        loadData()
    }
    //untuk load data yang tersimpan pada database yang sudah create data
    fun loadData() {
        val notes = db?.pembelianDao()!!.getPembelian()
        Log.d("MainActivity","dbResponse: $notes")
        pembelianAdapter.setData( notes )


    }
    fun setupListener() {
        btnCreate.setOnClickListener{
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }
    //pick data dari Id yang sebagai primary key
    fun intentEdit(noteId : Int, intentType: Int){
        startActivity(
            Intent(activity, ActivityEditPembelian::class.java)
                .putExtra("intent_id", noteId)
                .putExtra("intent_type", intentType)
        )
    }
}
