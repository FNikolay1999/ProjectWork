package com.example.projectwork.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PolyglotApi {

    //названия функций можешь потом изменить, если захочешь)
    //хз, что это
    @GET("countInfo.php")
    suspend fun getListOfLanguages() : Response<ListOfLanguages>

    @GET("dictionary.php")
    suspend fun getWordInfo(@Query("langId") langId : Long, @Query("id") id : Long) : Response<SingleWord>
}