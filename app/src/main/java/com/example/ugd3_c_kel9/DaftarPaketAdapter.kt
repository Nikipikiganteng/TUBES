package com.example.ugd3_c_kel9

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_c_kel9.databinding.ListDaftarPaketBinding

class DaftarPaketAdapter (
    private val listDaftarPaket:ArrayList<DaftarPaket>,
    private val context: Context
): RecyclerView.Adapter<DaftarPaketAdapter.DaftarPaketViewHolder>() { inner class
DaftarPaketViewHolder(item: ListDaftarPaketBinding): RecyclerView.ViewHolder(item.root){
    private val binding = item
    fun bind(DaftarPaketData: DaftarPaket){
        with (binding) {
            txtIdbl.text = DaftarPaketData.idbl
            txtNama.text = DaftarPaketData.nama
            cvData.setOnClickListener {
                var i = Intent(context,
                    DetailDaftarPaketActivity::class.java).apply {
                    putExtra("idbl",DaftarPaketData.idbl)
                }
                context.startActivity(i)
            }
        }
    }
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): DaftarPaketViewHolder {
        return DaftarPaketViewHolder(
            ListDaftarPaketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            ))
    }
    override fun onBindViewHolder(holder: DaftarPaketViewHolder, position: Int) { holder.bind(listDaftarPaket[position])
    }
    override fun getItemCount(): Int = listDaftarPaket.size
}