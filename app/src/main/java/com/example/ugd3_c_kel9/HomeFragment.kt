package com.example.ugd3_c_kel9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ugd3_c_kel9.entity.Studeer

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVStudeerAdapter = RVStudeerAdapter(Studeer.listOfStudeer)

        val rvStudeer : RecyclerView = view.findViewById(R.id.rv_sukucadang)

        rvStudeer.layoutManager = layoutManager

        rvStudeer.setHasFixedSize(true)

        rvStudeer.adapter =adapter
    }
}