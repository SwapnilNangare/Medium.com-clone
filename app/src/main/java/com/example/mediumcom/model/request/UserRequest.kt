package com.example.mediumcom.model.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("user") var user: User1
)

data class User1(
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String

)