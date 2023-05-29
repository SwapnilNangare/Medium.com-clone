package com.example.mediumcom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.model.response.UserResponse
import com.example.mediumcom.repository.UserRepository
import com.example.mediumcom.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(val repository: UserRepository) : ViewModel() {


    fun createUser(userRequest: UserRequest) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.createUser(userRequest)
        }

    }

    val user: LiveData<Resource<UserResponse>>
        get() = repository.userResponse


}