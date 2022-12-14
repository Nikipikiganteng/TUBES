package com.example.ugd3_c_kel9

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.ugd3_c_kel9.room.UserDB

class ProfileFragment : Fragment() {
    val db by lazy{activity?.let { UserDB(it )}  }

    var sharedPreferences: SharedPreferences? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = this.getActivity()?.getSharedPreferences("login", Context.MODE_PRIVATE)
        val nameTxt :TextView =  view.findViewById(R.id.tvname)
        val emailTxt :TextView =  view.findViewById(R.id.tvemail)
        val btnEdit : Button = view.findViewById(R.id.btnEdit)
        val id = sharedPreferences?.getString("id", "")
        nameTxt.setText(id?.toInt()?.let { db?.UserDao()?.getUser(it)!!.Username })
        emailTxt.setText(id?.toInt()?.let { db?.UserDao()?.getUser(it)!!.Email })

        btnEdit.setOnClickListener{
            val intent = Intent(requireActivity(), ActivityEditProfile::class.java)
            startActivity(intent)
//            (
        }

        val btnCam: ImageButton = view.findViewById(R.id.imageCam)
        btnCam.setOnClickListener{
            val moveCam = Intent(this@ProfileFragment.context, CameraActivity::class.java)
            startActivity(moveCam)
        }

    }
}