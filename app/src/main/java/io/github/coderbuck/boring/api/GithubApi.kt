package io.github.coderbuck.boring.api

import io.github.coderbuck.boring.bean.HotDevList
import io.github.coderbuck.boring.bean.HotRepoList
import retrofit2.Call
import retrofit2.http.GET


interface GithubApi {

    @GET("repositories?language=javascript&since=weekly")
    fun getHotRepoList(): Call<HotRepoList>

    @GET("developers?language=javascript&since=weekly")
    fun getHotDevList(): Call<HotDevList>
}