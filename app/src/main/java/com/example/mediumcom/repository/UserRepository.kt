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

    private val _userResponse = MutableLiveData<UserResponse>() //jo kahi response bhetnar aahe to
    val userResponse: LiveData<UserResponse> //public accessible
        get() = _userResponse

    suspend fun createUser(userRequest: UserRequest) { // request data
        try {
            val result = apiService.createUser(userRequest)
            if (result?.body() != null) {
                _userResponse.postValue(result.body())

            }
        } catch (e: IOException) {
       //     Resource.Error(message = UiMessage.StringResource(R.string.internet_connection))
        } catch (e: HttpException) {
          //  Resource.Error(message = UiMessage.StringResource(R.string.error_something_went_wrong))
        }

    }
}