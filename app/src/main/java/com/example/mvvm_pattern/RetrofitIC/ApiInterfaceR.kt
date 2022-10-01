package com.example.mvvm_pattern.RetrofitIC
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaceR {

    @GET("products")
    fun getProduct() : Call<List<ProductModelItem>>

    @GET("users")
    fun getUser() : Call<List<UserModelItem>>
}