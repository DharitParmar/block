package com.example.employeedirectory.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.employeedirectory.R
import com.example.employeedirectory.R.layout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}