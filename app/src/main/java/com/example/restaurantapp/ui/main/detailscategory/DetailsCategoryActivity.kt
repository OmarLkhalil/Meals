package com.example.restaurantapp.ui.main.detailscategory

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.data.base.BaseActivity
import com.example.restaurantapp.databinding.ActivityDetailsCategoryBinding
import java.net.URL

class DetailsCategoryActivity : BaseActivity<ActivityDetailsCategoryBinding, DetailsCategoryViewModel>(),
    Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.vm = viewModel

        val details = intent.getStringExtra("details")
        viewDataBinding.detailsTv.text = details

        val image = viewDataBinding.detailsIv
        val imageUrl = intent.getStringExtra("image")
        val url = URL(imageUrl)
        Glide.with(image).load(url).into(image)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_details_category
    }

    override fun initViewModel(): DetailsCategoryViewModel {
        return ViewModelProvider(this)[DetailsCategoryViewModel::class.java]
    }

}