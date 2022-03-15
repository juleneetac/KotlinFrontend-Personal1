package com.example.personalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.personalproject.storage.SharedPrefManager

class VerifyAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_account)

        val gologin=  findViewById<Button>(R.id.logButton)
        gologin.setOnClickListener({v -> goLogin()})
    }

    private fun goLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }

    override fun onStart() {  // se hace al inciar esta actividad
        super.onStart()

        if (!SharedPrefManager.getInstance(this).isLoggedIn){      //si no hay instancia de shared preferences te lleva a la pagina de login
            val gologin = Intent(applicationContext, LoginActivity::class.java)
            gologin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(gologin)
        }

        val user = SharedPrefManager.getInstance(applicationContext).user  //con esto se le pone los nombre guardados em el shared preferences
        findViewById<TextView>(R.id.textusername).setText(user.username)
        findViewById<TextView>(R.id.textemail).setText(user.email)
    }
}