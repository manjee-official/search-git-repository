package com.manjee.searchgitrepository.data.repository

import android.util.Log
import com.manjee.searchgitrepository.api.SearchApi
import com.manjee.searchgitrepository.data.model.RepositorySearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    companion object {
        val TAG = SearchRepositoryImpl::class.simpleName
    }

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
                } else {
                    when (response.code()) {
                        304 -> {
                            Log.e(TAG, "error 304 Not Modified")
                        }
                        304 -> {
                            Log.e(TAG, "error 422 Unprocessable Entity")
                        }
                        503 -> {
                            Log.e(TAG, "error 503 Service Unavailable")
                        }
                        else -> {
                            Log.e(TAG, "error")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RepositorySearchResponse>, t: Throwable) {
                fail(t)
            }
        })
    }
}