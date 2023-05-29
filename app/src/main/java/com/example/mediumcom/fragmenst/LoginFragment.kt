package com.example.mediumcom.fragmenst

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mediumcom.R

class LoginFragment : Fragment() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var textViewRegister:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)

        editTextEmail = view?.findViewById(R.id.editTextEmail)!!
        editTextPassword = view?.findViewById(R.id.editTextPassword)!!
        buttonLogin = view?.findViewById(R.id.buttonLogin)!!
        textViewRegister=view?.findViewById(R.id.textViewRegister)!!

        textViewRegister.setOnClickListener {
            Log.e("Log","Sign up")
            val intent = Intent(requireContext(), SignUpFragment::class.java)
            startActivity(intent)

        }

    }


}