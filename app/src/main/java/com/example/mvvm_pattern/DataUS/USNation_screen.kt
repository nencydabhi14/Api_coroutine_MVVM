package com.example.mvvm_pattern.DataUS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvvm_pattern.DataUS.ApiClientUS.Companion.getRetrofitUS
import com.example.mvvm_pattern.databinding.ActivityUsnationScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class USNation_screen : AppCompatActivity() {

    lateinit var usnationScreenBinding: ActivityUsnationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        usnationScreenBinding = ActivityUsnationScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(usnationScreenBinding.root)

        getUsIO()

    }

    fun getUsIO() {

        var apiInterfaceUS = getRetrofitUS().create(ApiInterfaceUS::class.java)

        apiInterfaceUS.getUSData("Nation", "Population").enqueue(object : Callback<USModelClass> {
            override fun onResponse(call: Call<USModelClass>, response: Response<USModelClass>) {
                var list = response.body()

                usnationScreenBinding.demoUs.text = list!!.source!![0]!!.annotations?.sourceDescription
            }

            override fun onFailure(call: Call<USModelClass>, t: Throwable) {
                Toast.makeText(this@USNation_screen, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}