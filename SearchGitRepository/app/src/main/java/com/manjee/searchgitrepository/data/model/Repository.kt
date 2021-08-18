package com.manjee.searchgitrepository.data.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val description: String
)