package com.example.mvvm_pattern.MVVMcoroutine

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        var Base_url = "https://jsonplaceholder.typicode.com/"

        fun getRetrofit(): Retrofit {
            var retrofit = Retrofit.Builder().baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }

    }
}