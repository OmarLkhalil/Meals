package com.example.data.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.restaurant.data.R

open abstract class BaseFragment <DB : ViewDataBinding, VM : BaseViewModel<*>> : Fragment(){

    lateinit var viewModel: VM
    lateinit var viewDataBinding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewModel = initViewModel()
        return viewDataBinding.root
    }


    abstract fun getLayoutId():Int
    abstract fun initViewModel():VM
}