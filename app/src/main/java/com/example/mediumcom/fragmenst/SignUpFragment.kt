package com.example.mediumcom.fragmenst

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mediumcom.R
import com.example.mediumcom.api.ApiService
import com.example.mediumcom.api.RetrofitHelper
import com.example.mediumcom.databinding.FragmentLoginBinding
import com.example.mediumcom.databinding.FragmentSignUpBinding
import com.example.mediumcom.model.request.User1
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.model.response.UserResponse
import com.example.mediumcom.repository.UserRepository
import com.example.mediumcom.utils.Resource
import com.example.mediumcom.viewmodel.MainViewModelFactory
import com.example.mediumcom.viewmodel.SignUpViewModel


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private lateinit var progressBar: ProgressBar

    private val binding get() = _binding!!
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)


        val editTextEmail = binding.editTextEmail
        val editTextPassword = binding.editTextPassword
        val buttonLogin = binding.buttonLogin
        progressBar =binding.progressBar


        val apiService = RetrofitHelper.createService(ApiService::class.java)
        val repository = UserRepository(apiService)

        signUpViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        ).get(SignUpViewModel::class.java)


        buttonLogin.setOnClickListener {
            createUser()
        }

        return binding.root
    }


    private fun createUser() {
        val editTextEmail = binding.editTextEmail
        val editTextPassword = binding.editTextPassword
        val editTextUserName = binding.editTextUserName

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
            when (it) {

                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE


                }

                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "Somethings went to wrong", Toast.LENGTH_SHORT)
                        .show()

                }

                is Resource.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "New User Created", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

                }
            }
        })


    }



}