package com.example.mediumcom.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mediumcom.R
import com.example.mediumcom.api.ApiService
import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.model.response.UserResponse
import com.example.mediumcom.utils.Resource
import com.example.mediumcom.utils.UiMessage
import retrofit2.HttpException
import java.io.IOException

class UserRepository(private val apiService: ApiService) {

    private val _userResponse = MutableLiveData<Resource<UserResponse>>()
    val userResponse: LiveData<Resource<UserResponse>>
        get() = _userResponse

    suspend fun createUser(userRequest: UserRequest) { // request data
        _userResponse.postValue(Resource.Loading())
        try {
            val result = apiService.createUser(userRequest)
            if (result?.body() != null) {
                _userResponse.postValue(Resource.Success(result.body()))

            } else {
                _userResponse.postValue(Resource.Loading())
                _userResponse.postValue(Resource.Error("Somethings went to wrong"))

            }
        } catch (e: IOException) {
            _userResponse.postValue(Resource.Error("No internet Connection"))

        } catch (e: HttpException) {
            _userResponse.postValue(Resource.Error(e.message.toString()))
        }

    }
}