package com.example.ugd3_c_kel9.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ugd3_c_kel9.*
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaket
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaketActivity
import com.example.ugd3_c_kel9.databinding.FragmentDaftarPaketBinding
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuruActivity

class DaftarPaketFragment : Fragment() {
    private var _binding: FragmentDaftarPaketBinding? = null
    // This property is only valid between onCreateView
// onDestroyView.
    private val binding get() = _binding!!
    private val listDaftarPaket = ArrayList<DaftarPaket>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDaftarPaketBinding.inflate(inflater, container, false)

        binding.buttonDaftarPaket.setOnClickListener {
            val intent = Intent(requireActivity(), DaftarPaketActivity::class.java)
            startActivity(intent)
        }
        binding.buttonDiskonPaket.setOnClickListener {
            val intent = Intent(requireActivity(), DiskonPaket::class.java)
            startActivity(intent)
        }
        binding.buttonPdf.setOnClickListener {
            val intent = Intent(requireActivity(), pdfActivity::class.java)
            startActivity(intent)
        }
        binding.buttonQr.setOnClickListener {
            val intent = Intent(requireActivity(), QRCodeActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}