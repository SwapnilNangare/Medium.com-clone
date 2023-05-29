package com.example.mediumcom.api

import com.example.mediumcom.model.request.UserRequest
import com.example.mediumcom.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    suspend fun createUser(@Body user: UserRequest): Response<UserResponse>
}