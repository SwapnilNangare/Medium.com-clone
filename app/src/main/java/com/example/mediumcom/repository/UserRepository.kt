package com.example.mediumcom.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediumcom.api.ApiService
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.model.response.UserResponse

class UserRepository(private val apiService: ApiService) {

    private val _userResponse = MutableLiveData<UserResponse>() //jo kahi response bhetnar aahe to
    val userResponse: LiveData<UserResponse> //public accessible
        get() = _userResponse

    suspend fun createUser(userRequest: UserRequest) { // request data

        val result = apiService.createUser(userRequest)
        if (result?.body() != null) {
            _userResponse.postValue(result.body())
        }
    }
}