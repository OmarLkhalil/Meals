package com.example.data.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val messageLiveData = MutableLiveData<String>()
    val showLoading     = MutableLiveData<Boolean>()
}