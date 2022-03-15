package com.example.personalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalproject.UserDataCollection.UserDataCollectionItem
import com.example.personalproject.UserDataCollection.UserInfo
import com.example.personalproject.UserDataCollection.UsersAdapter
import com.example.personalproject.retrofitService.RestEngine
import com.example.personalproject.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiles)
        getSupportActionBar()?.hide(); // para quitar la barra de título de esta página (lo que poner personalproyect)
    }



}


