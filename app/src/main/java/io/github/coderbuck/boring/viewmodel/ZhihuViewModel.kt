package io.github.coderbuck.boring.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.ZhihuHotItem
import io.github.coderbuck.boring.util.HtmlParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ZhihuViewModel : ViewModel() {

    val items = MediatorLiveData<List<ZhihuHotItem>>()

    fun request() {
        val call = Api.zhihu.getHots()
        call.enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("onResponse " + body)
                val zhihuHotList = HtmlParser.getZhihuHotList2(body)
                items.postValue(zhihuHotList)
            }
        })
    }

}
