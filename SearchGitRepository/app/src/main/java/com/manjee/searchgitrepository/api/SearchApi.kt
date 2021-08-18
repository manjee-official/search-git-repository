package com.manjee.searchgitrepository.api

import com.manjee.searchgitrepository.data.model.RepositorySearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/repositories")
    fun getRepositoryList(@Query("q") keyword: String): Call<RepositorySearchResponse>
}