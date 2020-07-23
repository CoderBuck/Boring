package io.github.coderbuck.boring.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ZhihuApi {

    companion object {
        const val BASE_URL = "https://www.zhihu.com/billboard"
    }

    @Headers("user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
    @GET("https://www.zhihu.com/billboard")
    suspend fun getHotListHtml(): String
}