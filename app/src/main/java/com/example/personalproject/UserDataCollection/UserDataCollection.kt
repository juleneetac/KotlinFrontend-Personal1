package com.example.personalproject.UserDataCollection

import com.google.gson.annotations.SerializedName

class UserDataCollection : ArrayList<UserDataCollectionItem>()

data class UserDataCollectionItem(
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?
    //val imageResource: Int
)

data class UserLogin(
    val username: String,
    val email: String,
    val password: String
)

