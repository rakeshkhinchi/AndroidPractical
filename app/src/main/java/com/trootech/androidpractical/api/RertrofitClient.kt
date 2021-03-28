package com.trootech.androidpractical.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RertrofitClient{

    private var retrofitService: RetrofitApi? = null
    private var BASEURL="https://api.invupos.com/invuApiPos/"

    fun getInstance() : RetrofitApi {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitApi::class.java)
        }
        return retrofitService!!
    }
}