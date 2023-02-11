package com.example.data.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open abstract class BaseFragment <DB : ViewDataBinding, VM : BaseViewModel> : Fragment(){

    lateinit var viewModel: VM
    lateinit var viewDataBinding: DB


    abstract fun getLayoutId():Int
    abstract fun initViewModel():VM
}