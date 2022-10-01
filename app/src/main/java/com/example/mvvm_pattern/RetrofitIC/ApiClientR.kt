package com.example.mvvm_pattern.RetrofitIC

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientR {

    companion object {
        var base_url = "https://fakestoreapi.com/"

        fun getRetrofit(): Retrofit {
            var retrofit = Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit
        }
    }
}