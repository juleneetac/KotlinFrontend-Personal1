package com.example.personalproject.retrofitService


import com.example.personalproject.UserDataCollection.UserDataCollectionItem
import com.example.personalproject.UserDataCollection.UserLogin
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// me he quedado en proceso de hacer esto
interface CryptoService {
    @GET("https://alpha-vantage.p.rapidapi.com/query?function=DIGITAL_CURRENCY_DAILY&symbol=ETH&market=EUR")
    fun parseData(response: String) {
        try {
            // your response
            val jsonObject = JSONObject(response)
            // get Time
            val time = jsonObject.getJSONObject("Time Series (Digital Currency Daily)")
            val iterator = time.keys()
            while (iterator.hasNext()) {
                // get date
                val date = iterator.next().toString()
                // get jsonobject by date tag
                val dateJson = time.getJSONObject(date)
                // get string
                val close = dateJson.getString("4a. close (EUR)")
                //Log.d("data", "4. close=$close")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}

