package com.example.mvvm_pattern.PaginationAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfacePagination {
    @GET("comments")

    fun getData(
        @Query("postId") postId: String
    ): Call<List<ApiModelClassitemItem>>
}