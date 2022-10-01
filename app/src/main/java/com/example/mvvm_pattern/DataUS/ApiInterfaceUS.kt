package com.example.mvvm_pattern.DataUS

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaceUS {

    @GET("data")
    fun getUSData(
        @Query("drilldowns") Nation: String,
        @Query("measures") Population: String
    ): Call<USModelClass>

}