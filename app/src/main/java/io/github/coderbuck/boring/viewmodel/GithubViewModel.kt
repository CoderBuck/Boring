package io.github.coderbuck.boring.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.coderbuck.boring.api.Api
import io.github.coderbuck.boring.bean.github.HotRepoList
import kotlinx.coroutines.launch
import timber.log.Timber

class GithubViewModel : ViewModel() {

    val hotRepoList = MutableLiveData<HotRepoList>()
    val refresh = MutableLiveData<Boolean>()

    init {
        refresh.value = false
    }

    fun request() {
        refresh.postValue(true)
        viewModelScope.launch {
            try {
                val repos = Api.github.getHotRepoList()
                refresh.postValue(false)
                hotRepoList.postValue(repos)
            } catch (e: Exception) {
                refresh.postValue(false)
                Timber.e(e, "onFailure")
            }
        }
    }
}