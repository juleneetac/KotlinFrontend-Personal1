package com.example.personalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.personalproject.storage.SharedPrefManager

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        getSupportActionBar()?.hide(); // para quitar la barra de título de esta página (lo que poner personalproyect)

        val gomarket=  findViewById<TextView>(R.id.gomarket)
        gomarket.setOnClickListener({v -> goMarket()})
    }

    private fun  goMarket(){
        startActivity(Intent(this,MarketActivity::class.java))
    }

    override fun onStart() {     // se hace al inciar esta actividad
        super.onStart()

        if (!SharedPrefManager.getInstance(this).isLoggedIn){      //si no hay instancia de shared preferences te lleva a la pagina de login
            val gologin = Intent(applicationContext, LoginActivity::class.java)
            gologin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(gologin)
        }

        val user = SharedPrefManager.getInstance(applicationContext).user  //con esto se le pone los nombre guardados em el shared preferences
        findViewById<TextView>(R.id.userTitle).setText(user.username)
        findViewById<TextView>(R.id.emailTitle).setText(user.email)
    }
}