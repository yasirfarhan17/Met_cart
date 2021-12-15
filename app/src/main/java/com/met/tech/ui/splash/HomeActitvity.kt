package com.met.tech.ui.splash

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.met.tech.R
import com.met.tech.base.BaseActivity
import com.met.tech.databinding.ActivityHomeBinding
import com.met.tech.injection.component.AppComponent
import com.met.tech.ui.splash.adapter.TabAapter


class HomeActitvity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun layoutId(): Int = R.layout.activity_home
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun injectActivity(appComponent: AppComponent) {
        appComponent.homeComponent().create().inject(this)
    }

    override fun addObservers() {
        observeCategoryList()
    }

    private fun observeCategoryList() {
       viewModel.getCategory.observe(this,{
           if(it!=null){
               setTab(it)
           }
       })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        clickListner()
    }

    private fun clickListner() {
        binding.appBar.imgBackButton.setOnClickListener {
            exitApp()
        }
        binding.appBar.imgNotification.setOnClickListener {
            showToast("You have zero notification")
        }
    }

    private fun initUi() {
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_CENTER
        binding.tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        val queue = Volley.newRequestQueue(this)
        viewModel.getCategory(queue)
    }



    fun setTab(items: List<CategoryModel>) {
        for (i in 0 .. items.size-1 ) {
            binding.tabLayout.addTab(
                binding.tabLayout.newTab().setText(items.get(i).category_name)
            )
        }
        val adapter = TabAapter( supportFragmentManager, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter
        binding.viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                binding.tabLayout
            )
        )

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    override fun onBackPressed() {
        exitApp()
    }

    private fun exitApp() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Close App?")
        builder.setIcon(R.mipmap.ic_launcher)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}