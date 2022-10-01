package com.example.mvvm_pattern.MVVMcoroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_pattern.MVVMcoroutine.ApiClient.Companion.getRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelclass : ViewModel() {

    var list = MutableLiveData<List<UserModelItem>>()

    init {
        viewModelScope.launch {
            async {
                list.postValue(GetAPI())
            }
        }
    }

    suspend fun GetAPI(): List<UserModelItem> {
        var apiInterface = getRetrofit().create(ApiInterface::class.java)

        return withContext(Dispatchers.IO) {
            apiInterface.getUser().body()!!
        }
    }
}