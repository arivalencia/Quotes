package com.ari.mvvm.data

import com.ari.mvvm.data.model.QuoteModel
import com.ari.mvvm.data.model.QuoteProvider
import com.ari.mvvm.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

   suspend fun getQuotes(): List<QuoteModel> {
       val quotes = api.getQuotes()
       QuoteProvider.quotes = quotes
       return quotes
   }

}