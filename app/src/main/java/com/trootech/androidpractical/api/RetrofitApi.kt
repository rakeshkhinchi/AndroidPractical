package com.trootech.androidpractical.api

import MenuModel
import com.trootech.androidpractical.model.StoreModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RetrofitApi {
    @Headers("APIKEY: bd_suvlascentralpos")
    @GET("index.php?r=configuraciones/franquicias")
    fun getStoreList(): Call<StoreModel>

    @GET("index.php?r=menu")
    fun getMenuList(@Header("APIKEY") header: String): Call<MenuModel>
}