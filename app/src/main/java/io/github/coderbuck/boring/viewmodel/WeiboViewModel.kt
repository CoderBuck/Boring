package io.github.coderbuck.boring.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.weibo.WeiboHotList
import io.github.coderbuck.boring.util.HtmlParser
import kotlinx.coroutines.launch
import timber.log.Timber

class WeiboViewModel : ViewModel() {

    val items = MutableLiveData<WeiboHotList>()
    val refresh = MutableLiveData<Boolean>()

    init {
        refresh.value = false
    }

    fun request() {
        refresh.postValue(true)
        viewModelScope.launch {
            try {
                val html = Api.weibo.getHotListHtml()
                val hots = HtmlParser.getWeiboHotList(html)
                refresh.postValue(false)
                items.postValue(hots)
            } catch (e: Exception) {
                refresh.postValue(false)
                Timber.e(e, "onFailure")
            }
        }
    }


}
