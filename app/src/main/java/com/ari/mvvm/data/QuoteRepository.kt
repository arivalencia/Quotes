package com.ari.mvvm.data

import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.data.model.QuoteProvider
import com.ari.mvvm.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
){
    suspend fun getQuotes(): List<QuoteModel> {
        val quotes = api.getQuotes()
        quoteProvider.quotes = quotes
        return quotes
    }

}