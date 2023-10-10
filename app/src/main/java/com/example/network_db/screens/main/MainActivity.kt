package com.example.network_db.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.network_db.core.App
import com.example.network_db.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.getInstance(this).appComponent.inject(this@MainActivity)
    }
}