package com.trootech.androidpractical.viewmodel

import MenuModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trootech.androidpractical.util.initLoading
import com.trootech.androidpractical.util.isOnline
import com.trootech.androidpractical.api.selectedItem
import com.trootech.androidpractical.model.StoreModel
import com.trootech.androidpractical.repository.MainRepository
import com.kaopiz.kprogresshud.KProgressHUD
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<StoreModel>()
    val menuList = MutableLiveData<MenuModel>()
    val menuListData = ArrayList<MenuModel.Data>()
    
    val errorMessage = MutableLiveData<String>()

    private lateinit var pd: KProgressHUD
    
    lateinit var selected_store: StoreModel.Franquicia
    
    fun setSelectedStore(item: StoreModel.Franquicia) {
        this.selected_store = item
        selectedItem=selected_store.aPIKEY
    }

    fun getAllStore(cont: Context) {
        
        if (isOnline(cont)){
            pd = initLoading(cont)
            pd.show()
            
            val response = repository.getStoreList()
            response.enqueue(object : Callback<StoreModel> {
                override fun onResponse(call: Call<StoreModel>, response: Response<StoreModel>) {
                    Log.e("Resonse..", "" + response.body())
                    movieList.postValue(response.body())
                    pd.dismiss()
                }

                override fun onFailure(call: Call<StoreModel>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    Log.e("Error.", "" + t.message)
                    pd.dismiss()
                }
            })    
        }else{
            Toast.makeText(cont,"No Internet!! Check Your Connecation",Toast.LENGTH_LONG).show()
        }
    }


    fun getMenuList(cont: Context) {
        if (isOnline(cont)) {
            pd = initLoading(cont)
            pd.show()

            val responseMenu = repository.getMenuList(selectedItem)
            responseMenu.enqueue(object : Callback<MenuModel> {
                override fun onFailure(call: Call<MenuModel>, t: Throwable) {
                    errorMessage.postValue(t.message)
                    Log.e("Error.", "" + t.message)
                    pd.dismiss()
                }

                override fun onResponse(call: Call<MenuModel>, response: Response<MenuModel>) {
                    Log.e("Resonse..Data", "" + response.body())
                    menuList.postValue(response.body())
                    menuListData.addAll(response.body()!!.data)
                    Log.e("List Menu", "" + menuList)
                    pd.dismiss()
                }
            })
        }else{
            Toast.makeText(cont,"No Internet!! Check Your Connecation",Toast.LENGTH_LONG).show()
        }
    }
}