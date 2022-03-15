package com.example.personalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.personalproject.UserDataCollection.UserDataCollectionItem
import com.example.personalproject.UserDataCollection.UsersAdapter
import com.example.personalproject.retrofitService.RestEngine
import com.example.personalproject.retrofitService.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val reciclerviewUsers = findViewById<RecyclerView>(R.id.ViewlistUsers)

        val boton=  findViewById<Button>(R.id.btnRetrofit)

        getSupportActionBar()?.hide(); // para quitar la barra de título de esta página (lo que poner personalproyect)

        boton.setOnClickListener({v -> callServiceGetUsers()})

//        val goprofiles=  findViewById<RelativeLayout>(R.id.relatlayoutlist)  //Para ir a la página de Profile
//        goprofiles.setOnClickListener(int pos){}
    }

//    private fun  goProfiles(){ //función para ir a la página de Profile de cualquier user
//        startActivity(Intent(this,ProfilesActivity::class.java))

 //   }


    private fun callServiceGetUsers(){
        val userService: UserService = RestEngine.getMyRestEngine().create(UserService::class.java)
        val dameUsers: Call<List<UserDataCollectionItem>> = userService.listUsers()


        dameUsers.enqueue(object  : Callback<List<UserDataCollectionItem>>{
            override fun onFailure(call: Call<List<UserDataCollectionItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<UserDataCollectionItem>>, response: Response<List<UserDataCollectionItem>>) {
                val userslist = response.body()

                userslist?.let {

                    showUsers(it)
                }
                Toast.makeText(this@MainActivity, "OK", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showUsers (users: List<UserDataCollectionItem>){
        val reciclerviewUsers = findViewById<RecyclerView>(R.id.ViewlistUsers)
        reciclerviewUsers.layoutManager = LinearLayoutManager(this)
        val adapter = UsersAdapter (users)
        reciclerviewUsers.adapter = adapter
        adapter.setOnItemClickListener(object : UsersAdapter.onItemClickListener{
            override fun onItemClick(position: Int) { // me da la posicion donde haces click
                val goprofile = Intent(applicationContext, ProfileActivity::class.java)
                goprofile.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(goprofile)
                //Toast.makeText(this@MainActivity, users.get(position).username, Toast.LENGTH_LONG).show()

            }
        })

    }


//    private fun GetUsersInfo(){
//        val userService: UserService = RestEngine.getRestEngine().create(UserService::class.java)
//        val dameUsers: Call<List<UserDataCollectionItem>> = userService.listUsers()
//
//
//        dameUsers.enqueue(object  : Callback<List<UserDataCollectionItem>>{
//            override fun onFailure(call: Call<List<UserDataCollectionItem>>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
//            }
//
//            override fun onResponse(call: Call<List<UserDataCollectionItem>>, response: Response<List<UserDataCollectionItem>>, int pos) {
//                val userslist = response.body()
//                UserDataCollectionItem user = userlist.get
//            }
//        })
//    }
}