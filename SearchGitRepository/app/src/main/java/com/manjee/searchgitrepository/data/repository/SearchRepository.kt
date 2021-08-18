package com.manjee.searchgitrepository.data.repository

import com.manjee.searchgitrepository.data.model.RepositorySearchResponse

interface SearchRepository {

    suspend fun getRepositoryList(
        keyword: String,
        success: (RepositorySearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    )
}