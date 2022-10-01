package com.example.mvvm_pattern.MVVMcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_pattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        var viewmodel = ViewModelProvider(this).get(ViewModelclass::class.java)
        viewmodel.list.observe(this, Observer {
            Log.e("TAG", "onCreate: ${it[0].company?.name}")
        })
    }
}