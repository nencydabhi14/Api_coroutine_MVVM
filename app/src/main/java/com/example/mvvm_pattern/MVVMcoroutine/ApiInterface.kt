package com.example.mvvm_pattern.MVVMcoroutine

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUser() : Response<List<UserModelItem>>
}