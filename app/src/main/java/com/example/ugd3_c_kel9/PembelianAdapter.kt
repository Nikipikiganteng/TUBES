package com.example.ugd3_c_kel9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_c_kel9.room.Pembelian
import kotlinx.android.synthetic.main.activity_pembelian_adapter.view.*

class PembelianAdapter (private val pembelian: ArrayList<Pembelian>, private val listener: OnAdapterListener) :
    RecyclerView.Adapter<PembelianAdapter.NoteViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_pembelian_adapter,parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = pembelian[position]
        holder.view.tvTitle.text = note.title
        holder.view.tvDate.text = note.date
        holder.view.tvActivity.text = note.activity

        holder.view.card.setOnClickListener{
            listener.onClick(note)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(note)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(note)
        }
    }

    override fun getItemCount() = pembelian.size
    inner class NoteViewHolder( val view: View) : RecyclerView.ViewHolder(view)
    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Pembelian>){
        pembelian.clear()
        pembelian.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(pembelian: Pembelian)
        fun onUpdate(pembelian: Pembelian)
        fun onDelete(pembelian: Pembelian)
    }
}