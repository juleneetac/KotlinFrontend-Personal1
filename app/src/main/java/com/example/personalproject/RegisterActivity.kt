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
import kotlinx.android.synthetic.main.activity_login.passText
import kotlinx.android.synthetic.main.activity_login.usernameText
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val gologin=  findViewById<TextView>(R.id.gologin)
        gologin.setOnClickListener({ v -> goLogin() })

        val regboton=  findViewById<Button>(R.id.regButton)
        regboton.setOnClickListener({ v -> Register() })


    }

    private fun goLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun Register() {
        val username = usernameText.text.toString().trim();
        val email = emailText.text.toString().trim();
        val pass = passText.text.toString().trim();
        val reppass = repeatpassText.text.toString().trim();


        if (username.isEmpty()) {
            usernameText.setError("Username required")       //se puede poner como abajo que será lo mismo
            usernameText.requestFocus()
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            emailText.error = "Email required or not correct format"
            emailText.requestFocus()
            return
        }

        if (pass.isEmpty()) {
            passText.error = "Passwsord required"
            passText.requestFocus()
            return
        }

        if (reppass.isEmpty()) {
            repeatpassText.error = "Passwsord repeated required"
            repeatpassText.requestFocus()
            return
        }

        if (!pass.equals(reppass)) {
            repeatpassText.setError("Password does not match")
        }

        else {


            var reguser = UserLogin(
                username,
                email,
                pass
            )  // creo el objeto para enviar los parametros de registro

            val userService: UserService =
                RestEngine.getMyRestEngine().create(UserService::class.java)
            //val login: Call<UserDataCollectionItem> = userService.login(usr)
            userService.register(reguser).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    if (response.isSuccessful) {
                        val responseuser = UserDataCollectionItem(username, email)
                        SharedPrefManager.getInstance(applicationContext).saveUser(responseuser)


                        val goverify = Intent(applicationContext, VerifyAccount::class.java)
                        goverify.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(goverify)
                    } else {
                        if (response.code() == 400)
                            Toast.makeText(
                                applicationContext,
                                "Bad Request (Error in input parameters' format)",
                                Toast.LENGTH_LONG
                            ).show();
                        else if (response.code() == 401)
                            Toast.makeText(
                                applicationContext,
                                "Conflict (Email already exists)",
                                Toast.LENGTH_LONG
                            ).show();
                        if (response.code() == 409)
                            Toast.makeText(
                                applicationContext,
                                "Conflict (User already exists)",
                                Toast.LENGTH_LONG
                            ).show();
                        else
                            Toast.makeText(
                                getApplicationContext(),
                                "Register error: " + response.code() + "\nInternal Server Error",
                                Toast.LENGTH_LONG
                            ).show();
                    }
                }

            })
        }
    }
}


