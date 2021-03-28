package com.trootech.androidpractical

import MenuModel
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.trootech.androidpractical.util.GridSpacingItemDecoration
import com.trootech.androidpractical.util.dpToPx
import com.trootech.androidpractical.adapter.MenuAdapter
import com.trootech.androidpractical.api.RertrofitClient
import com.trootech.androidpractical.repository.MainRepository
import com.trootech.androidpractical.repository.MyViewModelFactory
import com.trootech.androidpractical.strickelyheaderView.RecyclerSectionItemDecoration
import com.trootech.androidpractical.strickelyheaderView.SectionCallback
import com.trootech.androidpractical.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RertrofitClient.getInstance()
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        context = MenuActivity@ this

        setSupportActionBar(tool_bar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        recycler_view_menu.layoutManager = GridLayoutManager(context, 1)
        recycler_view_menu.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(0), true))


        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        adapter = MenuAdapter(context)
        viewModel.getMenuList(context)

        recycler_view_menu.adapter = adapter

        viewModel.menuList.observe(context as MenuActivity, Observer {
            adapter.setMenuItem(it.data)

        })

        val sectionItemDecoration = RecyclerSectionItemDecoration(
            resources.getDimensionPixelSize(R.dimen.header),
            true,
            getSectionCallback(viewModel.menuListData)!!
        )
        recycler_view_menu.addItemDecoration(sectionItemDecoration)
    }

    private fun getSectionCallback(menuList: ArrayList<MenuModel.Data>): SectionCallback? {
        return object : SectionCallback {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun isSection(position: Int): Boolean {
                return (position == 0 || menuList[position].categoria.nombremenu != menuList[position - 1].categoria.nombremenu)
            }

            override fun getSectionHeader(position: Int): CharSequence? {
                val text = menuList[position].categoria.nombremenu
                return text.subSequence(0, text.length)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}