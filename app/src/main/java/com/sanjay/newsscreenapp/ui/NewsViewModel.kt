package com.sanjay.newsscreenapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjay.newsscreenapp.model.Article
import com.sanjay.newsscreenapp.utils.NetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel: ViewModel() {

    private var _status = MutableLiveData<NetworkStatus>()
    val status:LiveData<NetworkStatus> = _status

    private var _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>> = _articleList

    fun getNews() = viewModelScope.launch {
        _status.value = NetworkStatus.Loading
        try {
            val response = NewsRepository.getArticles()
            _articleList.postValue(response.body()?.articles)
            _status.value = NetworkStatus.Success
        } catch (e:Exception) {
            _status.value = NetworkStatus.Error
        }

    }
}