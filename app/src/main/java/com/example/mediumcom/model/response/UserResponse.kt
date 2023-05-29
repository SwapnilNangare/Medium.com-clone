package com.example.mediumcom.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user") var user: User
)

data class User(

    @SerializedName("email") var email:String,
    @SerializedName("token") var token:String,
    @SerializedName("username") var username:String,
    @SerializedName("bio") var bio: String,
    @SerializedName("image") var image: String? = null

)