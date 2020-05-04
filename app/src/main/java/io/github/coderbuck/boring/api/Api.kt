package io.github.coderbuck.boring.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Api {

    private val githubRetrofit = Retrofit.Builder()
        .baseUrl(GithubApi.url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weiboRetrofit = Retrofit.Builder()
        .baseUrl(GithubApi.url)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val github = githubRetrofit.create(GithubApi::class.java)

    val weibo = weiboRetrofit.create(WeiboApi::class.java)
}