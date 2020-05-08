package io.github.coderbuck.boring.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface WeiboApi {

    companion object {
        const val base_url = "https://s.weibo.com/top/summary?cate=realtimehot"

    }

    @Headers("user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
    @GET("https://s.weibo.com/top/summary?cate=realtimehot")
    fun getHotRepoList(): Call<String>
}