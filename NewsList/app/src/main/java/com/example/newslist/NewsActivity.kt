package com.example.newslist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newslist.NewsListActivity.Companion.local

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        title = localClassName

        val details = findViewById<Button>(R.id.details)

        details.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

        val logout = findViewById<Button>(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val about = findViewById<Button>(R.id.about)

        about.setOnClickListener {
            val url = "https://news.google.com"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        //val intent = intent
        var textLogin = findViewById<TextView>(R.id.textLoginNews)
        textLogin.text = local.toString()
        /*if (intent.hasExtra("login")) {
           //textLogin.text  = intent.getStringExtra("login")
        }*/



    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}