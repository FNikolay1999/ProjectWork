package com.example.projectwork.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

class PolyglotService {

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                .apply { level = HttpLoggingInterceptor.Level.BASIC }
            ).build()
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api by lazy {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("http://mmcspolyglot.mcdir.ru/")
            .build()
            .create(PolyglotApi::class.java)
    }

    suspend fun getListOfLanguages() : ListOfLanguages?{
        return safeCall(
            api.getListOfLanguages()
        )
    }

    suspend fun getWordInfo(langId : Long, id : Long) : SingleWord?{
        return safeCall(
            api.getWordInfo(langId, id)
        )
    }

    private suspend inline fun <T> safeCall(
        req: Response<T>
    ): T? {
        return when {
            req.isSuccessful -> req.body()
            else -> {
                Log.d("Request", "Не успешно. код ошибки ${req.code()}")
                null
            }
        }
    }


}