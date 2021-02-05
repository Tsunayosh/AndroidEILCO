package com.example.tp6

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td6.GithubService
import com.example.td6.RepoAdapter
import com.example.td6.Repos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var nomRepo = intent.getStringExtra("login")

        val githubService = Retrofit.Builder()
            .baseUrl(GithubService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubService::class.java)

        githubService.searchRepos(nomRepo)?.enqueue(object : Callback<Repos?> {
            override fun onResponse(call: Call<Repos?>, response: Response<Repos?>) {
                afficherRepos(response.body())
            }

            override fun onFailure(call: Call<Repos?>, t: Throwable) {
            }
        })
    }
    fun afficherRepos(repos: Repos?) {
        val rvRepos = findViewById<View>(R.id.rvRepo) as RecyclerView
        val adapter = RepoAdapter(repos, this@ResultActivity)
        rvRepos.adapter = adapter
        rvRepos.layoutManager = LinearLayoutManager(this)
    }
}