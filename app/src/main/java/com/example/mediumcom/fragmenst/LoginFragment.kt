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
import androidx.navigation.fragment.findNavController
import com.example.mediumcom.R
import com.example.mediumcom.databinding.ActivityMainBinding.inflate
import com.example.mediumcom.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)


        val textViewRegister = binding.textViewRegister

        textViewRegister.setOnClickListener {
            Log.e("Log", "Sign up")
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        return binding.root

    }


}