package com.ari.mvvm.data.network

import com.ari.mvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {

    @GET(".json")
    suspend fun getQuotes(): Response<List<QuoteModel>>

}