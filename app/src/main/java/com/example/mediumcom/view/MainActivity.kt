package com.example.mediumcom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mediumcom.R
import com.example.mediumcom.api.ApiService
import com.example.mediumcom.api.RetrofitHelper
import com.example.mediumcom.databinding.ActivityMainBinding
import com.example.mediumcom.model.request.User1
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.repository.UserRepository
import com.example.mediumcom.viewmodel.MainViewModelFactory
import com.example.mediumcom.viewmodel.SignUpViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // setContentView(R.layout.activity_main)
        val view = binding.root
        setContentView(view)

    }
}