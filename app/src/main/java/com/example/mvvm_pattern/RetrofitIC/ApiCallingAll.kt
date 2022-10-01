package com.example.mvvm_pattern.RetrofitIC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_pattern.RetrofitIC.ApiClientR.Companion.getRetrofit
import com.example.mvvm_pattern.databinding.ActivityApiCallingAllBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiCallingAll : AppCompatActivity() {

    lateinit var apiCallingAllBinding: ActivityApiCallingAllBinding

    var listproduct = arrayListOf<ProductModelItem>()
    var listfilter = arrayListOf<ProductModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        apiCallingAllBinding = ActivityApiCallingAllBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(apiCallingAllBinding.root)

        getUSerModel()
        getProductItems()

        apiCallingAllBinding.filter0200.setOnClickListener {
            Filterbetween0to200()
        }

        apiCallingAllBinding.filter50200.setOnClickListener {
            Filterbetween50to100()
        }

        apiCallingAllBinding.filterMorethan200.setOnClickListener {
            Filtermorethan200()
        }
    }

    fun Filterbetween0to200() {
        getProductItemsFilter(0.000,50.000)
    }
    fun Filterbetween50to100(){
        getProductItemsFilter(50.000,100.000)
    }
    fun Filtermorethan200(){
        getProductItemsFilter(200.000,1000.000)
    }
    fun getProductItems(){
        var apiInterfaceR = getRetrofit().create(ApiInterfaceR::class.java)

        apiInterfaceR.getProduct().enqueue(object : Callback<List<ProductModelItem>> {
            override fun onResponse(
                call: Call<List<ProductModelItem>>, response: Response<List<ProductModelItem>>
            ) {

                listproduct = response.body() as ArrayList<ProductModelItem>
                var ans = listproduct!!.get(0)!!.rating!!.rate
                Log.e("TAG", "Response: ${ans}")

                apiCallingAllBinding.productTxt.text = response.body().toString()



                setUpProduct(listproduct)
            }

            override fun onFailure(call: Call<List<ProductModelItem>>, t: Throwable) {
                Toast.makeText(this@ApiCallingAll, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getProductItemsFilter(i2: Double, i1: Double) {

        listfilter.clear()

        var i = 0
        while (i < listproduct!!.size) {
            if (listproduct[i].price!!.toDouble()>i2 && listproduct[i].price!!.toDouble()<i1){
                listfilter.add(listproduct[i])
            }
            i++
        }
        setUpProduct(listfilter)

    }


    fun getUSerModel() {
        var apiInterfaceR = getRetrofit().create(ApiInterfaceR::class.java)
        apiInterfaceR.getUser().enqueue(object : Callback<List<UserModelItem>> {
            override fun onResponse(
                call: Call<List<UserModelItem>>, response: Response<List<UserModelItem>>
            ) {
                var listuser = response.body()
                var ans = listuser!![0].address?.geolocation?.jsonMemberLong
                Log.e("TAG", "onResponse==========================================: $ans")

                apiCallingAllBinding.userTxt.text = response.body().toString()
            }

            override fun onFailure(call: Call<List<UserModelItem>>, t: Throwable) {
                Log.e("TAG", "onResponse: ${t.message}")
            }
        })
    }


    fun setUpProduct(listproduct: List<ProductModelItem>) {
        var productAdaptor = ProductAdaptor(this@ApiCallingAll, listproduct)
        var lm = LinearLayoutManager(this)
        apiCallingAllBinding.productRecyclerview.adapter = productAdaptor
        apiCallingAllBinding.productRecyclerview.layoutManager = lm
    }
}