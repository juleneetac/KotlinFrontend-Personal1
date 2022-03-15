package com.example.personalproject.retrofitService

import com.example.personalproject.UserDataCollection.UserDataCollectionItem
import com.example.personalproject.UserDataCollection.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST




interface UserService {

    @GET("usermanager/users")
    fun listUsers(): Call<List<UserDataCollectionItem>>

    @POST("usermanager/login")
    fun login(@Body usr: UserLogin): Call<UserDataCollectionItem>

    @POST("usermanager/register")
    fun register(@Body usr: UserLogin): Call<Void>

}