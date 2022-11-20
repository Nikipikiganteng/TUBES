package com.example.ugd3_c_kel9.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ugd3_c_kel9.*
import com.example.ugd3_c_kel9.databinding.FragmentDaftarPaketBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class DaftarPaketFragment : Fragment() {
    private var _binding: FragmentDaftarPaketBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val listDaftarPaket = ArrayList<DaftarPaket>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaftarPaketBinding.inflate(inflater,
            container, false)
        return binding.root
        getDaftarPaket()
    }

    override fun onStart() {
        super.onStart()
        getDaftarPaket()
    }

    private fun getDaftarPaket() {
        binding.rvData.setHasFixedSize(true)
        binding.rvData.layoutManager = LinearLayoutManager(context)
        val bundle = arguments
        val cari = bundle?.getString(/* key = */ "cari")
        binding.progressBar.visibility

        RClient.instances.getData(cari).enqueue(object : Callback<ResponseDaftarPaket> {
            override fun onResponse(
                call: Call<ResponseDaftarPaket>,
                response: Response<ResponseDaftarPaket>
            ){
                if (response.isSuccessful){
                    listDaftarPaket.clear()
                    response.body()?.let { listDaftarPaket.addAll(it.data) }
                    val adapter = DaftarPaketAdapter(listDaftarPaket, requireContext())
                    binding.rvData.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }
            override fun onFailure(call: Call<ResponseDaftarPaket>, t: Throwable) {

            }
        }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}