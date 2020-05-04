package io.github.coderbuck.boring.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    val BASE_URL_GITHUB = "https://ghapi.huchen.dev/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(GithubApi.url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val github = retrofit.create(GithubApi::class.java)
}