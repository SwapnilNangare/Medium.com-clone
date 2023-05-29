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
import com.example.mediumcom.model.request.User1
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.repository.UserRepository
import com.example.mediumcom.viewmodel.MainViewModelFactory
import com.example.mediumcom.viewmodel.SignUpViewModel

class MainActivity : AppCompatActivity() {

    lateinit var signUpViewModel: SignUpViewModel


    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUserName = findViewById(R.id.editTextUserName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        val apiService = RetrofitHelper.createService(ApiService::class.java)
        val repository = UserRepository(apiService)

        signUpViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(SignUpViewModel::class.java)


        buttonLogin.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {

        if (editTextUserName.text.toString().isEmpty()) {
            Toast.makeText(this, "Username is Empty", Toast.LENGTH_SHORT).show()

            return
        } else if (editTextEmail.text.toString().isEmpty()) {
            Toast.makeText(this, "Email is Empty", Toast.LENGTH_SHORT).show()
            return

        } else if (editTextPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show()

            return
        }

        var user = User1(
            username = editTextUserName.text.toString(),
            email = editTextEmail.text.toString(),
            password = editTextPassword.text.toString()
        )

        signUpViewModel.createUser(UserRequest(user))

        signUpViewModel.user.observe(this, Observer {
            Log.e("TAG", "New User Created")
        })


    }
}