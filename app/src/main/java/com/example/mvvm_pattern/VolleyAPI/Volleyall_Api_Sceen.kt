package com.example.mvvm_pattern.VolleyAPI

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mvvm_pattern.databinding.ActivityVolleyallApiSceenBinding


class Volleyall_Api_Sceen : AppCompatActivity() {

    var requestQueue: RequestQueue? = null
    var Api_Post = "https://reqres.in/api/users"
    var Get_volley = "https://jsonplaceholder.typicode.com/posts"


    var listvolley = arrayListOf<PostVolleyModel>()

    lateinit var volleyallApiSceenBinding: ActivityVolleyallApiSceenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        volleyallApiSceenBinding = ActivityVolleyallApiSceenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(volleyallApiSceenBinding.root)

//        postApi("nency", "Android")
        GetVolley()
    }

    fun postApi(name: String, job: String) {

        requestQueue = Volley.newRequestQueue(this)

        var stringRequest = object : StringRequest(Method.POST, Api_Post, Response.Listener {
            Toast.makeText(this, "SuccessFully Created Job", Toast.LENGTH_SHORT).show()
        }, Response.ErrorListener {
            Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
        }) {

            override fun getParams(): MutableMap<String, String>? {

                var map = HashMap<String, String>()
                map["name"] = "$name"
                map["job"] = "$job"

                return map
            }
        }
        requestQueue!!.add(stringRequest)
    }

    fun GetVolley() {
        requestQueue = Volley.newRequestQueue(this)

        var jsonArrayRequest = JsonArrayRequest(Method.GET, Get_volley, null, { responce ->
            volleyallApiSceenBinding.responseTxt.text = responce.toString()

            var i = 0
            while (i < responce.length()) {
                var userId = responce.getJSONObject(i).getString("userId")
                var id = responce.getJSONObject(i).getString("id")
                var title = responce.getJSONObject(i).getString("title")
                var body = responce.getJSONObject(i).getString("body")

                var m1 = PostVolleyModel(userId, id, title, body)
                listvolley.add(m1)

                Log.e("TAG", "GetVolley=========================================================================================:$title")
                volleyallApiSceenBinding.responseTxt.text = title

                i++
            }
        }, { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
        })
        requestQueue!!.add(jsonArrayRequest)
    }
}