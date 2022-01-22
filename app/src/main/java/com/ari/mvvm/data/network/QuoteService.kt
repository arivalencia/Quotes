package com.ari.mvvm.data.network

import com.ari.mvvm.core.ApiClient
import com.ari.mvvm.data.model.QuoteModel

class QuoteService  {

    private val client = ApiClient.getClient().create(QuoteApiClient::class.java)

    suspend fun getQuotes(): List<QuoteModel> {
        val response = client.getQuotes()
        return response.body() ?: listOf()
    }
}