package com.example.personalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalproject.UserDataCollection.UserDataCollectionItem
import com.example.personalproject.UserDataCollection.UserLogin
import com.example.personalproject.retrofitService.RestEngine
import com.example.personalproject.retrofitService.UserService
import com.example.personalproject.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goregister=  findViewById<TextView>(R.id.goregister)
        goregister.setOnClickListener({v -> goRegister()})

        val logboton=  findViewById<Button>(R.id.logButton)
        logboton.setOnClickListener({v -> Login() })
    }

    private fun  goRegister(){
        startActivity(Intent(this,RegisterActivity::class.java))
    }

    private fun Login() {
        val usernamemail = usernameText.text.toString().trim();
        val password = passText.text.toString().trim();

        if (usernamemail.isEmpty()) {
            usernameText.error = "Username or email required"
            usernameText.requestFocus()
            return
        }

        if (password.isEmpty()) {
            passText.error = "Passwsord required"
            passText.requestFocus()
            return
        }

        var loguser = UserLogin("", "", "")
        val resultado: Boolean = usernamemail.contains("@")  //si contiene arroba a la variable le llamare email, sino le llamare username
        if (resultado){
            val email = usernamemail
            loguser = UserLogin("", email, password)
        }
        else{
            val username = usernamemail
            loguser = UserLogin(username,"", password)
        }



        val userService: UserService = RestEngine.getMyRestEngine().create(UserService::class.java)
        //val login: Call<UserDataCollectionItem> = userService.login(usr)
        userService.login(loguser).enqueue(object : Callback<UserDataCollectionItem> {
            override fun onFailure(call: Call<UserDataCollectionItem>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<UserDataCollectionItem>, response: Response<UserDataCollectionItem>) {
                if(response.isSuccessful){
                    val responseuser = UserDataCollectionItem(response.body()?.username!!, response.body()?.email!!)
                    SharedPrefManager.getInstance(applicationContext).saveUser(responseuser)

                    val gomain = Intent(applicationContext, MainActivity::class.java)
                    gomain.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(gomain)
                }
                else{
                    if(response.code() == 404 || response.code() == 402)
                        Toast.makeText(applicationContext, "Authentication error: 404 Invalid username/email or password" , Toast.LENGTH_LONG).show();
                    if(response.code() == 409)
                        Toast.makeText(applicationContext, "Authentication error: 409 User not verified" , Toast.LENGTH_LONG).show();
                    if(response.code() == 500)
                        Toast.makeText(getApplicationContext(), "Authentication error: " + response.code() + "\nInternal Server Error", Toast.LENGTH_LONG).show();
                }
            }

        })
    }

//    override fun onStart() {   //cuando estes loggeado que pase directamente a la pestaña de perfil
//        super.onStart()
//
//        if (SharedPrefManager.getInstance(this).isLoggedIn){
//            val goprofile = Intent(applicationContext, ProfileActivity::class.java)
//            goprofile.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(goprofile)
//        }
//    }
}

