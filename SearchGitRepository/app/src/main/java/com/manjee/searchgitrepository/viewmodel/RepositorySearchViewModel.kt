package com.manjee.searchgitrepository.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manjee.searchgitrepository.data.model.Repository
import com.manjee.searchgitrepository.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    companion object {
        val TAG = RepositorySearchViewModel::class.simpleName
    }

    private val _getRepositoryLiveData = MutableLiveData<ArrayList<Repository>>()
    val getRepositoryLiveData: LiveData<ArrayList<Repository>> = _getRepositoryLiveData

    fun getRepositoryList(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getRepositoryList(
                keyword = keyword,
                success = {
                    _getRepositoryLiveData.postValue(it.items)
                },
                fail = { throwable ->
                    Log.e(TAG, "$throwable")
                }
            )
        }
    }
}