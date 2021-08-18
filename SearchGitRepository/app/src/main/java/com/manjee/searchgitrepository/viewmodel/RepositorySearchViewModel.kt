package com.manjee.searchgitrepository.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getRepositoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getRepositoryList(
                keyword = "manjee",
                success = {

                },
                fail = { throwable ->
                    Log.e(TAG, "$throwable")
                }
            )
        }
    }
}