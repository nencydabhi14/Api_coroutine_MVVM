package com.example.mvvm_pattern.PaginationAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientPagination {

    companion object {
        var base_url = "https://jsonplaceholder.typicode.com/"

        fun getRetrofit(): Retrofit {

            var retrofit = Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }
}