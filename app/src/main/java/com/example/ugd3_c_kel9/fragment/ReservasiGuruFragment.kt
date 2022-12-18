package com.example.ugd3_c_kel9.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugd3_c_kel9.QRCodeActivity
import com.example.ugd3_c_kel9.R
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaket
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaketActivity
import com.example.ugd3_c_kel9.databinding.FragmentDaftarPaketBinding
import com.example.ugd3_c_kel9.databinding.FragmentReservasiGuruBinding
import com.example.ugd3_c_kel9.pdfActivity
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuruActivity

class ReservasiGuruFragment : Fragment() {
    private var _binding: FragmentReservasiGuruBinding? = null
    // This property is only valid between onCreateView
// onDestroyView.
    private val binding get() = _binding!!
    private val listReservasiGuru = ArrayList<ReservasiGuru>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReservasiGuruBinding.inflate(inflater, container, false)

        binding.buttonReservasiGuru.setOnClickListener {
            val intent = Intent(requireActivity(), ReservasiGuruActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}