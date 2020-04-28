package io.github.coderbuck.boring.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Api {

    val BASE_URL_GITHUB = "https://ghapi.huchen.dev/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GITHUB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val github = retrofit.create(GithubApi::class.java)
}