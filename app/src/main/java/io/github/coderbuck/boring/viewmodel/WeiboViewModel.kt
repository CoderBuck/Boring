package io.github.coderbuck.boring.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import io.github.coderbuck.boring.util.HtmlParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeiboViewModel : ViewModel() {

    val items = MediatorLiveData<WeiboHotList>()

    val refresh = MediatorLiveData<Boolean>()

    init {
        refresh.value = false
    }

    fun request() {
        val call = Api.weibo.getHotRepoList()
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                refresh.postValue(false)
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                refresh.postValue(false)
                Timber.d("onResponse")
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("onResponse " + body)
                val weiboHotList = HtmlParser.getWeiboHotList(body)
                items.postValue(weiboHotList)
            }
        })
    }

}
