package io.github.coderbuck.boring.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.github.HotRepoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class GithubViewModel : ViewModel() {

    val hotRepoList = MutableLiveData<HotRepoList>()

    val refresh = MediatorLiveData<Boolean>()

    init {
        refresh.value = false
    }

    fun request() {
        val call = Api.github.getHotRepoList()
        call.enqueue(object : Callback<HotRepoList?> {
            override fun onFailure(call: Call<HotRepoList?>, t: Throwable) {
                refresh.postValue(false)
                Timber.w("onFailure")
            }

            override fun onResponse(call: Call<HotRepoList?>, response: Response<HotRepoList?>) {
                refresh.postValue(false)
                Timber.d("onResponse")
                val body = response.body()
                if (body == null) {
                    Timber.w("body == null")
                    return
                }
                Timber.d("body.size = ${body.size}")
                hotRepoList.postValue(body)
            }

        })
    }
}