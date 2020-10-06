package com.example.newslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = localClassName

        val ok = findViewById<Button>(R.id.ok)

        ok.setOnClickListener(){
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
        finish()
    }
}