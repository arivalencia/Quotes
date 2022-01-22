package com.ari.mvvm.data.network

import com.ari.mvvm.core.ApiClient
import com.ari.mvvm.data.model.QuoteModel
import javax.inject.Inject

class QuoteService @Inject constructor(
    private val api: QuoteApiClient
) {

    suspend fun getQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        return response.body() ?: listOf()
    }
}