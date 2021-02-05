package com.example.tp6

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boutonOK = findViewById<View>(R.id.boutonOK) as Button
        val nomRepo = findViewById<View>(R.id.nomRepo) as EditText

        boutonOK.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("nomRepo", nomRepo.text)
            startActivity(intent)
            }
        }
}