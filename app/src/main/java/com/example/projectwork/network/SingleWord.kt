package com.example.projectwork.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleWord(
    @Json(name = "id") val id: Long,
    @Json(name = "word") val originWord: String,
    @Json(name = "transcription") val transcription: String,
    @Json(name = "translation") val translation: String,
    @Json(name = "picture") val imgSrcUrl: String
)