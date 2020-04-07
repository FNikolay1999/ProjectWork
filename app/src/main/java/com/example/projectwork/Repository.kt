package com.example.projectwork

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository(val userPreferences: UserPreferences) {

    private val BASE_URL = "https://api.jikan.moe/v3/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val client = OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()
    /*
    private var api: AnimeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(AnimeApi::class.java)
     */
}