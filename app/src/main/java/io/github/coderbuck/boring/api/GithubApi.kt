package io.github.coderbuck.boring.api

import io.github.coderbuck.boring.bean.github.HotDevList
import io.github.coderbuck.boring.bean.github.HotRepoList
import retrofit2.Call
import retrofit2.http.GET


interface GithubApi {

    companion object {
        const val BASE_URL = "https://ghapi.huchen.dev/"
    }

    @GET("repositories?language=javascript&since=weekly")
    suspend fun getHotRepoList(): HotRepoList

    @GET("developers?language=javascript&since=weekly")
    fun getHotDevList(): Call<HotDevList>
}