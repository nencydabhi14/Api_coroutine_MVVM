package com.example.mvvm_pattern.PaginationAPI

import com.google.gson.annotations.SerializedName

data class ApiModelClassitem(

	@field:SerializedName("ApiModelClassitem")
	val apiModelClassitem: List<ApiModelClassitemItem?>? = null
)

data class ApiModelClassitemItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("postId")
	val postId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
