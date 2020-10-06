package com.example.newslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.login_activity.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        title = localClassName
        login
        val ok = findViewById<Button>(R.id.ok)
        var textLogin = findViewById<TextView>(R.id.loginDetails)
        textLogin.text = NewsListActivity.local.toString()

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