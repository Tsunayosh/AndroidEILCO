package com.example.newslist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.newslist.NewsListActivity.Companion.local

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        title = localClassName

        val login = findViewById<Button>(R.id.login)

        login.setOnClickListener(){
            val editLogin = findViewById<EditText>(R.id.loginText)
            val textLogin = editLogin.text.toString()
            local = textLogin
            val intent = Intent(this, NewsActivity::class.java)
            //intent.putExtra("login", textLogin)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}