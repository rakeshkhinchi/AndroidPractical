package com.trootech.androidpractical.repository

import com.trootech.androidpractical.api.RetrofitApi

class MainRepository( var retrofitService: RetrofitApi) {
    fun getStoreList() = retrofitService.getStoreList()

    fun getMenuList(_header:String)=retrofitService.getMenuList(_header)
}