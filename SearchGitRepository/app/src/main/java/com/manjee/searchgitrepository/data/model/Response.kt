package com.manjee.searchgitrepository.data.model

import com.google.gson.annotations.SerializedName

data class RepositorySearchResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: ArrayList<Repository>
)