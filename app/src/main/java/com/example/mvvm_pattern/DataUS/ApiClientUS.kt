package com.example.mvvm_pattern.DataUS

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientUS {

    companion object {


        var BASE_URL = "https://datausa.io/api/"

        fun getRetrofitUS(): Retrofit {
            var retrofit =
                Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit
        }
    }

}