package com.example.restaurantapp.ui.detailsmeal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.data.base.BaseFragment
import com.example.restaurantapp.databinding.FragmentMealsDetailsBinding
import java.net.URL

class DetailsMealsFragment : BaseFragment<FragmentMealsDetailsBinding, DetailsMealViewModel>(), Navigator {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding.vm = viewModel
        viewModel.navigator = this

        val details = Intent.getStringExtra("details")
        viewDataBinding.detailsMealTv.text = details

        val image = viewDataBinding.detailsMealIv
        val imageUrl = intent.getStringExtra("image")
        val url = URL(imageUrl)
        Glide.with(image).load(url).into(image)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_meals_details
    }

    override fun initViewModel(): DetailsMealViewModel {
        return ViewModelProvider(this)[DetailsMealViewModel::class.java]
    }


}
