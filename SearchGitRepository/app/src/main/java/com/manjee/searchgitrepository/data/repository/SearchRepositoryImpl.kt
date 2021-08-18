package com.manjee.searchgitrepository.data.repository

import com.manjee.searchgitrepository.api.SearchApi
import com.manjee.searchgitrepository.data.model.RepositorySearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    override suspend fun getRepositoryList(
        keyword: String,
        success: (RepositorySearchResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        searchApi.getRepositoryList(keyword).enqueue(object : Callback<RepositorySearchResponse> {
            override fun onResponse(
                call: Call<RepositorySearchResponse>,
                response: Response<RepositorySearchResponse>
            ) {
                if (response.code() == 200) {
                    success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<RepositorySearchResponse>, t: Throwable) {
                fail(t)
            }
        })
    }
}