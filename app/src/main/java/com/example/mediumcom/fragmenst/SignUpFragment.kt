package com.example.mediumcom.fragmenst

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


class SignUpFragment : Fragment() {

    lateinit var signUpViewModel: SignUpViewModel


    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)

        editTextUserName = view?.findViewById(R.id.editTextUserName)!!
        editTextEmail = view?.findViewById(R.id.editTextEmail)!!
        editTextPassword = view?.findViewById(R.id.editTextPassword)!!
        buttonLogin = view?.findViewById(R.id.buttonLogin)!!

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
            Toast.makeText(requireContext(), "Username is Empty", Toast.LENGTH_SHORT).show()

            return
        } else if (editTextEmail.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Email is Empty", Toast.LENGTH_SHORT).show()
            return

        } else if (editTextPassword.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Password is Empty", Toast.LENGTH_SHORT).show()

            return
        }

        var user = User1(
            username = editTextUserName.text.toString(),
            email = editTextEmail.text.toString(),
            password = editTextPassword.text.toString()
        )

        signUpViewModel.createUser(UserRequest(user))

        signUpViewModel.user.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", "New User Created")
        })


    }


}