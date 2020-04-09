package com.example.projectwork.network

import com.squareup.moshi.Json

data class ListOfLanguages (
    @Json(name = "1") val count: Long,
    @Json(name = "2") val languages: String,
    @Json(name = "3") val wordsCount: String
)