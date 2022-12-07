package com.example.ugd3_c_kel9.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd3_c_kel9.*
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaket
import com.example.ugd3_c_kel9.daftarpaket.DaftarPaketAdapter
import com.example.ugd3_c_kel9.daftarpaket.RClient
import com.example.ugd3_c_kel9.daftarpaket.ResponseDaftarPaket
import com.example.ugd3_c_kel9.databinding.FragmentDataDaftarPaketBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class DataDaftarPaketFragment : Fragment() {
    private var _binding: FragmentDataDaftarPaketBinding? = null
    private val binding get() = _binding!!
    private val listDestination = ArrayList<DaftarPaket>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataDaftarPaketBinding.inflate(inflater, container, false)
        return binding.root
        getDataDestination()
    }
    override fun onStart() {
        super.onStart()
        getDataDestination()
    }
    private fun getDataDestination() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager= LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility
        RClient.instances.getData(cari).enqueue(object :
            Callback<ResponseDaftarPaket> {
            override fun onResponse(
                call: Call<ResponseDaftarPaket>,
                response: Response<ResponseDaftarPaket>
            ){
                if (response.isSuccessful){
                    listDestination.clear()
                    response.body()?.let {
                        listDestination.addAll(it.data) }
                    val adapter =
                        DaftarPaketAdapter(listDestination, requireContext() )
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDaftarPaket>, t:
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