package com.example.ugd3_c_kel9.reservasiguru

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.DetailReservasiGuruActivity
import com.example.ugd3_c_kel9.databinding.ListReservasiGuruBinding

class ReservasiGuruAdapter (
    private val listReservasiGuru:ArrayList<ReservasiGuru>,
    private val context: Context
    ): RecyclerView.Adapter<ReservasiGuruAdapter.ReservasiGuruViewHolder>() { inner class
    ReservasiGuruViewHolder(item: ListReservasiGuruBinding): RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(ReservasiGuruData: ReservasiGuru){
            with (binding) {
                txtIdrsv.text = ReservasiGuruData.idrsv
                txtNama.text = ReservasiGuruData.nama
                cvData.setOnClickListener {
                    var i = Intent(context,
                        DetailReservasiGuruActivity::class.java).apply {
                        putExtra("idrsv",ReservasiGuruData.idrsv)
                    }
                    context.startActivity(i)
                }
            }
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType:
        Int): ReservasiGuruViewHolder {
            return ReservasiGuruViewHolder(
                ListReservasiGuruBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,false
                ))
        }
        override fun onBindViewHolder(holder: ReservasiGuruViewHolder, position: Int) { holder.bind(listReservasiGuru[position])
        }
        override fun getItemCount(): Int = listReservasiGuru.size
}