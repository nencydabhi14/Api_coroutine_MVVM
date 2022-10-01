package com.example.mvvm_pattern.PaginationAPI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.mvvm_pattern.PaginationAPI.ClientPagination.Companion.getRetrofit
import com.example.mvvm_pattern.databinding.ActivityReadDataScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadDataScreen : AppCompatActivity() {

    var paginationAdaptor: PaginationAdaptor? = null
    lateinit var readDataScreenBinding: ActivityReadDataScreenBinding

    var i: Int = 1
    var list = arrayListOf<ApiModelClassitemItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        readDataScreenBinding = ActivityReadDataScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(readDataScreenBinding.root)

        LastScroll()
        getDataPagitaion("$i")
        setUpRV(list)
    }

    private fun LastScroll() {
        readDataScreenBinding.paginationRecyclerview.addOnScrollListener(object :
            OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                var layoutManager =
                    readDataScreenBinding.paginationRecyclerview.layoutManager as LinearLayoutManager
                var position = layoutManager.findLastVisibleItemPosition()
                var itemcount: Int =
                    readDataScreenBinding.paginationRecyclerview.adapter!!.itemCount


                if (position == itemcount - 1) {
                    i++
                    getDataPagitaion("$i")
                }
            }
        })
    }

    fun getDataPagitaion(page: String) {

        var interfacePagination = getRetrofit().create(InterfacePagination::class.java)

        interfacePagination.getData("$page").enqueue(object : Callback<List<ApiModelClassitemItem>> {
            override fun onResponse(
                call: Call<List<ApiModelClassitemItem>>,
                response: Response<List<ApiModelClassitemItem>>
            ) {
                list.addAll(response.body() as ArrayList<ApiModelClassitemItem>)
                paginationAdaptor!!.notifyDataSetChanged()
                Log.e("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<List<ApiModelClassitemItem>>, t: Throwable) {
                Toast.makeText(this@ReadDataScreen, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun setUpRV(list: List<ApiModelClassitemItem>) {
        paginationAdaptor = PaginationAdaptor(this,list)
        var lm = LinearLayoutManager(this)
        readDataScreenBinding.paginationRecyclerview.layoutManager = lm
        readDataScreenBinding.paginationRecyclerview.adapter = paginationAdaptor
    }

}