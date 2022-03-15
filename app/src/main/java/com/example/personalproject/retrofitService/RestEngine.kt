package com.example.personalproject.retrofitService

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestEngine {
    companion object{   //parecido a clases staticas en java
        fun getMyRestEngine(): Retrofit{
            val interceptor= HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val clientSetup = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)  //le añado estas cosas porque sino a la hora de enviar el mail de verificacion,
                .readTimeout(1, TimeUnit.MINUTES)  // se queda sin tiempo y me daria error
                .build()
            //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/project/")    //aqui no sirve poner localhost, necesitas una ip que se conecte a internet
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientSetup)
                .build()
            return retrofit
        }

        fun getCryptoRestEngine(): Retrofit{
            val interceptor= HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val clientSetup = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)  //le añado estas cosas porque sino a la hora de enviar el mail de verificacion,
                .readTimeout(1, TimeUnit.MINUTES)  // se queda sin tiempo y me daria error
                .build()
            //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://alpha-vantage.p.rapidapi.com/")    //aqui no sirve poner localhost, necesitas una ip que se conecte a internet
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientSetup)
                .build()
            return retrofit
        }
    }
}