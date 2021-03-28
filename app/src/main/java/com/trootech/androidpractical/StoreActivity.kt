package com.trootech.androidpractical

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.trootech.androidpractical.adapter.StoreAdapter
import com.trootech.androidpractical.api.RertrofitClient
import com.trootech.androidpractical.model.StoreModel
import com.trootech.androidpractical.repository.MainRepository
import com.trootech.androidpractical.repository.MyViewModelFactory
import com.trootech.androidpractical.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RertrofitClient.getInstance()
    private lateinit var adapter: StoreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        context = MainActivity@ this
        recycler_view_store.layoutManager = GridLayoutManager(context, 1)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        adapter = StoreAdapter() { it: StoreModel.Franquicia ->
            viewModel.setSelectedStore(it)

            val intent = Intent(context, MenuActivity::class.java)
            startActivity(intent)
        }
        viewModel.getAllStore(context)
        recycler_view_store.adapter = adapter
        viewModel.movieList.observe(context as StoreActivity, androidx.lifecycle.Observer {
            adapter.setStoreList(it.franquicia)
            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                recycler_view_store.layoutManager = GridLayoutManager(context, 1)
                recycler_view_store.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )


            } else {
                recycler_view_store.layoutManager = GridLayoutManager(context, 2)
                recycler_view_store.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )

            }
        })
    }
}