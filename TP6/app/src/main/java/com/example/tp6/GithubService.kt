package com.example.td6

import com.example.tp6.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/users/{user}/repos")
    fun listRepos(@Path("user") User: String?): Call<List<Repo?>?>?

    @GET("/search/repositories")
    fun searchRepos(@Query("q") Query: String?): Call<Repos?>?

    companion object {
        const val ENDPOINT = "https://api.github.com"
    }
}