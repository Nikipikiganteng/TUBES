package com.example.ugd3_c_kel9.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd3_c_kel9.R
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ReservasiGuruAdapter
import com.example.ugd3_c_kel9.reservasiguru.RClientReservasiGuru
import com.example.ugd3_c_kel9.reservasiguru.ResponseReservasiGuru
import com.example.ugd3_c_kel9.databinding.FragmentDataReservasiGuruBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class DataReservasiGuruFragment : Fragment() {
    private var _binding: FragmentDataReservasiGuruBinding? = null
    private val binding get() = _binding!!
    private val listReservasiGuru = ArrayList<ReservasiGuru>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataReservasiGuruBinding.inflate(inflater, container, false)
        return binding.root
        getDataReservasiGuru()
    }
    override fun onStart() {
        super.onStart()
        getDataReservasiGuru()
    }
    private fun getDataReservasiGuru() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility
        RClientReservasiGuru.instances.getData(cari).enqueue(object :
            Callback<ResponseReservasiGuru> {
            override fun onResponse(
                call: Call<ResponseReservasiGuru>,
                response: Response<ResponseReservasiGuru>
            ){
                if (response.isSuccessful){
                    listReservasiGuru.clear()
                    response.body()?.let {
                        listReservasiGuru.addAll(it.data) }
                    val adapter =
                        ReservasiGuruAdapter(listReservasiGuru, requireContext() )
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseReservasiGuru>, t:
            Throwable) {
            }
        }
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}